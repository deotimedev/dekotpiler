package me.deo.dekotpiler.translation.impl

import me.deo.dekotpiler.mapping.TypeMappings
import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.model.KtTypeParameter
import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.type.KtReferenceType
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.processing.Processing
import me.deo.dekotpiler.processing.Processor
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRStatement
import me.deo.dekotpiler.util.gather
import me.deo.dekotpiler.util.resolveTypeParameter
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaArrayTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaGenericRefTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
@Component
internal class TranslationImpl(
    translators: List<Translator<*, *>>,
    private val processing: Processing,
    private val typeMappings: TypeMappings
) : Translation {
    private val translatorsByType = translators.groupBy { resolveTypeParameter(it::class, Translator::class, "J")!! }
    override fun session() = SessionImpl()
    override fun <C : Any, K> translators(type: KClass<out C>) = translatorsByType[type].orEmpty() as List<Translator<C, K>>

    private val computedFunctions = mutableMapOf<MethodPrototype, KtFunction>()

    inner class SessionImpl : Translation.Session {

        private val computedVariables = ConcurrentHashMap<LValue, KtVariable>()
        private fun <K : Any> translate(value: Any) =
            translators<Any, K>(value::class)
                .find { with(it) { value.match() } }
                ?.run { translation(value) }
                ?.let { translated ->
                    fun processors(obj: Any) =
                        processing.processors(Processor.Mode.Pre, obj::class) as? List<Processor<Any>>

                    var result: Any = translated
                    processors(translated).gather { list ->
                        list.find {
                            if (!with(it) { result.match() }) return@find false
                            val old = result::class.java
                            result = it.replace(result)!!
                            old != result::class.java
                        }?.let { processors(result) }
                    }
                    result as K
                }

        override fun translateExpression(expression: CFRExpression): KtExpression =
            translate(expression) ?: KtUnknown(expression)

        override fun translateStatement(statement: CFRStatement): KtStatement =
            translate(statement) ?: KtUnknown(statement)

        override fun translateStatement(statement: Op04StructuredStatement): KtStatement =
            translate(statement) ?: KtUnknown(statement)

        override fun translateBlock(statement: CFRStatement) =
            translateStatement(statement).asBlock()

        override fun translateBlock(statement: Op04StructuredStatement) =
            translateStatement(statement).asBlock()

        override fun <V : KtVariable> translateVariable(variable: LValue): V =
            ((computedVariables.computeIfAbsent(variable) { translate(variable)!! }) as V).also {
                if (it is KtLocalVariable) it.uses++
            }

        override fun translateType(type: JavaTypeInstance): KtType =
            if (type is JavaArrayTypeInstance) KtType.array(translateType(type.arrayStrippedType))
            else typeMappings.mapping(type) ?: KtReferenceType(
                type.deGenerifiedType.rawName,
                generics = (type as? JavaGenericRefTypeInstance)?.genericTypes.orEmpty().map(::translateType)
            )

        override fun translateArrayType(type: JavaTypeInstance) =
            translateType(type.arrayStrippedType).nullable(false)

        override fun translateConditional(conditional: ConditionalExpression): KtConditional =
            translate(conditional) ?: KtConditional(KtUnknown(conditional))

        // this eventually needs to be able to resolve a function from other
        // class files if provided
        override fun translateFunction(
            function: MethodPrototype,
            kind: KtFunction.Kind
        ) = KtFunction(
            function.name,
            function.originalDescriptor,
            null, // evaluate receiver in post processing
            (if (function.parametersComputed())
                function.parameterLValues.map {
                    KtFunction.Parameter(
                        it.localVariable.name.stringName,
                        translateType(it.localVariable.inferredJavaType.javaTypeInstance),
                    )
                } else
                function.args.map { KtFunction.Parameter(null, translateType(it)) }).toMutableList(),
            function.formalParameterMap.map { (name, param) ->
                KtTypeParameter(
                    name,
                    listOf(translateType(param.bound))
                )
            }.toMutableList(),
            translateType(function.returnType).nullable(kind != KtFunction.Kind.Constructor),
            translateType(function.classType).nullable(false) as? KtReferenceType,
            kind
        )
    }
}
package com.deotime.dekotpiler.translation.impl

import com.deotime.dekotpiler.mapping.TypeMappings
import com.deotime.dekotpiler.metadata.MetadataLocator
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.KtUnknown
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.dekotpiler.processing.Processing
import com.deotime.dekotpiler.processing.Processor
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import com.deotime.dekotpiler.util.CFRExpression
import com.deotime.dekotpiler.util.CFRStatement
import com.deotime.dekotpiler.util.gather
import com.deotime.dekotpiler.util.resolveTypeParameter
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaArrayTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaGenericRefTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype
import org.benf.cfr.reader.entities.Method
import org.koin.core.annotation.Single
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
@Single
internal class TranslationImpl(
    translators: List<Translator<*, *>>,
    private val processing: Processing,
    private val typeMappings: TypeMappings,
    private val metadata: MetadataLocator,
) : Translation {
    private val translatorsByType = translators.groupBy { it.resolveTypeParameter<Translator<*, *>>("J")!! }
    override fun session() = SessionImpl()
    override fun <C : Any, K> translators(type: KClass<out C>) =
        translatorsByType[type].orEmpty() as List<Translator<C, K>>

    private val computedFunctions = mutableMapOf<MethodPrototype, KtFunction>()

    inner class SessionImpl : Translation.Session {

        private val computedRawFunctions = ConcurrentHashMap<MethodPrototype, KtFunctionDescriptor.Raw>()
        private val computedFunctions = ConcurrentHashMap<Method, KtFunction>()
        private val computedVariables = ConcurrentHashMap<LValue, KtVariable>()

        private fun <K : Any> translate(value: Any) =
            translators<Any, K>(value::class)
                .find { with(it) { value.match() } }
                ?.run { translation(value) }
                ?.let { translated ->
                    fun processors(obj: Any) =
                        processing.processors(Processor.Mode.Pre, obj::class) as? List<Processor<Any>>

                    // TODO: This process is weird and needs to be changed
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
            translateType(type.arrayStrippedType).nullable(false) // todo should this be nullable?

        override fun translateConditional(conditional: ConditionalExpression): KtConditional = translate(conditional)!!

        override fun translateFunction(
            function: MethodPrototype,
        ) = computedRawFunctions.computeIfAbsent(function) {
            KtFunctionDescriptor.Raw(
                name = function.name,
                descriptor = function.originalDescriptor,
                enclosing = translateType(function.classType) as KtReferenceType,
                returns = translateType(function.returnType),
                parameters = function.args.map(::translateType)
            )
        }

    }
}
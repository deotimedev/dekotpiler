package me.deo.dekotpiler.translation.impl

import me.deo.dekotpiler.mapping.TypeMappings
import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.processing.Processing
import me.deo.dekotpiler.processing.Processor
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRStatement
import me.deo.dekotpiler.util.gather
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConditionalExpression
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
@Suppress("UNCHECKED_CAST")
internal class TranslationImpl(
    translators: List<Translator<*, *>>,
    private val processing: Processing,
    private val typeMappings: TypeMappings
) : Translation {
    private val translatorsByType = translators.groupBy { it.type.java }

    override fun <C : Any, K> translators(type: KClass<out C>) =
        translatorsByType[type.java].orEmpty() as List<Translator<C, K>>

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

    override fun <V : KtVariable> translateVariable(variable: LValue): V = (translate(variable) as? V) ?: error("what")
    override fun translateType(type: JavaTypeInstance) = typeMappings.mapping(type) ?: KtType(type, true)
    override fun translateConditional(conditional: ConditionalExpression) =
        KtConditional(translateExpression(conditional), null)
}
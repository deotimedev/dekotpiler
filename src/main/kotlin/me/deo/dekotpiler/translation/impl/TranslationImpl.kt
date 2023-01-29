package me.deo.dekotpiler.translation.impl

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.translation.provided.Op04StatementTranslator
import me.deo.dekotpiler.util.CFRExpression
import me.deo.dekotpiler.util.CFRStatement
import me.deo.dekotpiler.util.CFRVariable
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType
import kotlin.math.exp
import kotlin.reflect.KClass

@Component
class TranslationImpl(
    translators: List<Translator<*, *>>
) : Translation {
    private val translatorsByType = translators.associateBy {
        it.type.java
    }

    override fun <C : Any, K> translator(type: KClass<out C>) =
        (translatorsByType[type.java] as? Translator<C, K>)

    private fun <K> translate(value: Any) =
        translator<Any, K>(value::class)?.run { translation(value) }

    override fun translateExpression(expression: CFRExpression): KtExpression = translate(expression) ?: KtUnknown(expression.toString())
    override fun translateStatement(statement: CFRStatement): KtStatement = translate(statement) ?: KtUnknown(statement.toString())
    override fun translateStatement(statement: Op04StructuredStatement): KtStatement = translate(statement) ?: KtUnknown(statement.toString())
    override fun translateVariable(variable: CFRVariable): KtVariable = translate(variable) ?: error("what")
}
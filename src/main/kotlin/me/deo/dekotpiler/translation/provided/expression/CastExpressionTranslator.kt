package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.springframework.stereotype.Component

@Component
class CastExpressionTranslator : Translator<CastExpression, KtCastExpression> {
    override val type = CastExpression::class
    override fun Translation.translation(value: CastExpression) =
        KtCastExpression(
            translateExpression(value.child),
            translateType(value.inferredJavaType.javaTypeInstance)
        )
}
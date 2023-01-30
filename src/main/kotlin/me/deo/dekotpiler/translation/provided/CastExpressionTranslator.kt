package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.model.expressions.KtInstanceOfExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.InstanceOfExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class CastExpressionTranslator : ExpressionTranslator<CastExpression, KtCastExpression> {
    override val type = CastExpression::class
    override fun Translation.translation(value: CastExpression) =
        KtCastExpression(
            translateExpression(value.child),
            value.inferredJavaType.javaTypeInstance
        )
}
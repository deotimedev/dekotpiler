package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class TernaryExpressionTranslator : ExpressionTranslator<TernaryExpression, KtIfElseExpression> {
    override val type = TernaryExpression::class
    override fun Translation.translation(value: TernaryExpression) =
        KtIfElseExpression(translateExpression(value.condition), translateExpression(value.lhs), translateExpression(value.rhs))
}
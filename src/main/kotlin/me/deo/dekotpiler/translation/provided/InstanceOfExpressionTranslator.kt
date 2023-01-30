package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.model.expressions.KtInstanceOfExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.InstanceOfExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class InstanceOfExpressionTranslator : ExpressionTranslator<InstanceOfExpression, KtInstanceOfExpression> {
    override val type = InstanceOfExpression::class
    override fun Translation.translation(value: InstanceOfExpression) =
        KtInstanceOfExpression(
            translateExpression(value.lhs),
            translateType(value.typeInstance)
        )
}
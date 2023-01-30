package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.model.expressions.KtInstanceOfExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.InstanceOfExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class BooleanExpressionTranslator : ExpressionTranslator<BooleanExpression, KtConditional> {
    override val type = BooleanExpression::class
    override fun Translation.translation(value: BooleanExpression) = translateConditional(value)
}
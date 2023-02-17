package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtComparison(
    var reference: KtExpression,
    var comparing: KtExpression,
    var mode: Type
) : KtConditional(reference) {
    override val expressionView: KtExpressionView = views(::reference, ::comparing)

    override fun code() = buildCode {
        write(reference, " ${mode.symbol} ", comparing)
    }

    enum class Type(val symbol: String) {
        ReferenceEquality("==="),
        NotReferenceEquality("!=="),
        LessThan("<"),
        GreaterThan(">"),
        LessThanOrEqual("<="),
        GreaterThanOrEqual(">=")
    }
}
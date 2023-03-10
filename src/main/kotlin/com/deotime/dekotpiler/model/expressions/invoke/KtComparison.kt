package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.vision

data class KtComparison(
    var reference: KtExpression,
    var comparing: KtExpression,
    var mode: Type
) : KtConditional(reference) {
    override val expressionView: KtExpressionView = vision(::reference, ::comparing)

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
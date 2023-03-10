package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.vision

data class KtWhenExpression(
    var value: KtExpression,
    val branches: MutableList<Branch>,
) : KtExpression {
    override val expressionView: KtExpressionView = vision(::value)

    // TODO need a way to determine common types
    override val type: KtType get() = branches.first().expression.type

    data class Branch(
        var targets: MutableList<KtExpression>, // if empty then this branch is an `else`
        var expression: KtExpression
    )
}
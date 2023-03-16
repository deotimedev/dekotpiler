package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

data class KtWhenExpression(
    var value: KtExpression,
    val branches: MutableList<Branch>,
) : KtExpression {
    override val sight = vision(::value) // todo branches

    // TODO need a way to determine common types
    override val type: KtType get() = branches.first().expression.type

    data class Branch(
        var targets: MutableList<KtExpression>, // if empty then this branch is an `else`
        var expression: KtExpression,
    )
}
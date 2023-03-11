package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.vision.vision

data class KtNotNullAssertionExpression(
    var expression: KtExpression,
) : KtExpression {
    override val sight = vision(::expression)
    override val type get() = expression.type.nullable(false)
    override fun code() = buildCode {
        write(expression, "!!")
    }
}
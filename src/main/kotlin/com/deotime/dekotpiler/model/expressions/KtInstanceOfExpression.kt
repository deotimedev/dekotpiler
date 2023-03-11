package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

data class KtInstanceOfExpression(
    var expression: KtExpression,
    var typeCheck: KtType
) : KtExpression {
    override val sight = vision(::expression)
    override val type = KtType.Boolean
    override fun code() = buildCode {
        write(expression, " is ", typeCheck)
    }
}
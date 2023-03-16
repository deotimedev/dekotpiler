package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

data class KtIsExpression(
    var expression: KtExpression,
    var typeCheck: KtType,
) : KtConditional {
    override var inverse = false
    override val sight = vision(::expression)
    override fun code() = buildCode {
        write(expression, " ${if (inverse) "!" else ""}is ", typeCheck)
    }
}
package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

data class KtCastExpression(
    var expression: KtExpression,
    var cast: KtType,
) : KtExpression {
    override val sight = vision(::expression)
    override val type get() = cast
    override fun code() = buildCode {
        braced { write(expression, " as ", cast) }
    }
}
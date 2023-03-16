package com.deotime.dekotpiler.model.expressions.conditional

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional.Companion.writeNegated
import com.deotime.vision.vision

data class KtConditionalLike(
    var expression: KtExpression,
) : KtConditional {
    override var inverse = false
    override val sight = vision(::expression)
    override fun code() = buildCode {
        writeNegated()
        +expression
    }
}
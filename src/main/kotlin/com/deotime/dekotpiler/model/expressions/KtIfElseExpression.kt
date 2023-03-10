package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.vision.vision

data class KtIfElseExpression(
    var condition: KtConditional,
    var then: KtExpression,
    var orElse: KtExpression
) : KtExpression {
    override val sight = vision(::condition, ::then, ::orElse)

    // TODO need a way to find a common type
    override val type get() = then.type
    override fun code() = buildCode {
        write("if ")
        braced { +condition }
        write(" ", then, " else ", orElse)
    }
}
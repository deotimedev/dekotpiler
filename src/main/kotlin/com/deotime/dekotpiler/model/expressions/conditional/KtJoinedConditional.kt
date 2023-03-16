package com.deotime.dekotpiler.model.expressions.conditional

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional.Companion.writeNegated
import com.deotime.vision.vision

data class KtJoinedConditional(
    var left: KtConditional,
    var right: KtConditional,
    var op: Operation,
) : KtConditional {

    override var inverse = false

    override val sight = vision(::left, ::right)

    override fun code() = buildCode {
        writeNegated()
        braced { write(left, " ${op.symbol} ", right) }
    }

    enum class Operation(val symbol: String) {
        Or("||"),
        And("&&")
    }
}
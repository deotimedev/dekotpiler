package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.gather
import com.deotime.vision.blurred
import com.deotime.vision.vision

// todo this should really be an interface
open class KtConditional(
    var underlying: KtExpression,
    var joined: Joined? = null,
    var inverse: Boolean = false
) : KtExpression {

    final override val type = KtType.Boolean

    // todo rework without joined
    override val sight = vision(::underlying) + blurred(::joined)

    override fun code() = buildCode {
        if (inverse) +"!"
        +underlying

        joined.gather { it.conditional.joined }
            .forEach {
                write(" ${it.operation.symbol} ", it.conditional)
            }
    }

    inner class Joined(
        var operation: Operation,
        val conditional: KtConditional
    ) : KtExpression by conditional {
        val with = this@KtConditional
        override val type: KtType
            get() = conditional.type

    }

    enum class Operation(val symbol: String) {
        Or("||"),
        And("&&")
    }
}
package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.util.gather

open class KtConditional(
    var underlying: KtExpression,
    var joined: Joined? = null,
    var inverse: Boolean = false
) : KtExpression {

    override val type = KtType.Boolean

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
        var conditional: KtConditional
    ) {
        val with = this@KtConditional
    }

    enum class Operation(val symbol: String) {
        Or("||"),
        And("&&")
    }
}
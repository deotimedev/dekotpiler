package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.util.gather
import me.deo.dekotpiler.util.view
import me.deo.dekotpiler.util.views

open class KtConditional(
    var underlying: KtExpression,
    var joined: Joined? = null,
    var inverse: Boolean = false
) : KtExpression {

    override val type = KtType.Boolean

    override val expressionView: KtExpressionView = views(::underlying, ::joined)
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
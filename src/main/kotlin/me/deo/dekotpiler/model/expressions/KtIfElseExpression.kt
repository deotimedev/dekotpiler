package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode

data class KtIfElseExpression(
    val condition: KtConditional,
    val then: KtExpression,
    val orElse: KtExpression
) : KtExpression {
    // TODO need a way to find a common type
    override val type get() = then.type
    override fun code() = buildCode {
        write("if ")
        braced { +condition }
        write(" ", then, " else ", orElse)
    }
}
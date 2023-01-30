package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.codeWriter

data class KtConditional(
    var underlying: KtExpression,
    var joined: Joined?
) : KtExpression {

    override val type = KtType.Boolean

    override fun writeCode() = codeWriter {
        if (joined == null) write(underlying)
        else {
            write("(")
            write(underlying)
            generateSequence(this@KtConditional.joined) { it.conditional.joined }
                .forEach {
                    write(" ${it.operation.symbol} ", it.conditional, ")")
                }
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
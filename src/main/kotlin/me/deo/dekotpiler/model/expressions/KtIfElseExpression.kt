package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.translation.codeWriter

data class KtIfElseExpression(
    val condition: KtExpression, // change to condition
    val then: KtExpression,
    val orElse: KtExpression
) : KtExpression {
    override fun asCode() = codeWriter {
        write("if ")
        braced(condition)
        write(" ", then, " else ", orElse)
    }
}
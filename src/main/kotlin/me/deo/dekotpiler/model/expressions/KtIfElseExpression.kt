package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.codeWriter

data class KtIfElseExpression(
    val condition: KtConditional,
    val then: KtExpression,
    val orElse: KtExpression
) : KtExpression {
    // TODO need a way to find a common type
    override val type get() = then.type
    override fun writeCode() = codeWriter {
        write("if ")
        braced(condition)
        write(" ", then, " else ", orElse)
    }
}
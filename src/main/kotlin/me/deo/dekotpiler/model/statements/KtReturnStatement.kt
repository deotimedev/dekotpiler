package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.buildCode

data class KtReturnStatement(
    var expression: KtExpression?,
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"return"
        writeLabel()
        expression?.let { write(" ", it) }
    }
}
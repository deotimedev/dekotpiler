package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.translation.buildCode

data class KtBreakStatement(
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"break"
        writeLabel()
    }
}
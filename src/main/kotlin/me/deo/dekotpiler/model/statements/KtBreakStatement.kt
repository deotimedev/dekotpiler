package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.coding.buildCode

data class KtBreakStatement(
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"break"
        writeLabel()
    }
}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.buildCode

data class KtContinueStatement(
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"continue"
        writeLabel()
    }
}
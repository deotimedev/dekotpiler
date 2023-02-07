package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.buildCode

data class KtContinueStatement(
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"continue"
        writeLabel()
    }
}
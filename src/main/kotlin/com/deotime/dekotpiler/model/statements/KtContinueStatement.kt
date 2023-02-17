package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode

data class KtContinueStatement(
    override var label: String? = null
) : KtLabelled {
    override fun code() = buildCode {
        +"continue"
        writeLabel()
    }
}
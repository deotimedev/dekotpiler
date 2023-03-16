package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.statements.KtOptionalLabel.Companion.writeLabel

data class KtBreakStatement(
    override var label: String? = null,
) : KtJumpingStatement {
    override fun code() = buildCode {
        +"break"
        writeLabel()
    }
}
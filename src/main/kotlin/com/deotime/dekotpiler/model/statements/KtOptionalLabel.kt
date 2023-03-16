package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.CodeBuilder

interface KtOptionalLabel {
    var label: String? // TODO should be changed to a scope name reference

    companion object {
        context (CodeBuilder)
        fun KtOptionalLabel.writeLabel() = apply { label?.let { write('@', it) } }
    }
}
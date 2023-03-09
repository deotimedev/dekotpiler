package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.type.KtNothingType

interface KtOptionalLabel {
    var label: String? // TODO should be changed to a scope name reference

    companion object {
        context (CodeBuilder)
        fun KtOptionalLabel.writeLabel() = apply { label?.let { write('@', it) } }
    }
}
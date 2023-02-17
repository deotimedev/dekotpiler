package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.type.KtNothingType

interface KtLabelled : KtStatement {
    var label: String? // TODO should be changed to a lambda reference

    override val type get() = KtNothingType
    fun Code.writeLabel() = apply { label?.let { write('@', it) } }
}
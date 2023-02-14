package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.model.type.KtNothingType

interface KtLabelled : KtStatement {
    var label: String? // TODO should be changed to a lambda reference

    override val type get() = KtNothingType
    fun Code.writeLabel() = apply { label?.let { write('@', it) } }
}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.Code

interface KtLabelled : KtStatement {
    var label: String? // TODO should be changed to a lambda reference

    override val type get() = KtType.Nothing
    fun Code.writeLabel() = apply { label?.let { write('@', it) } }
}
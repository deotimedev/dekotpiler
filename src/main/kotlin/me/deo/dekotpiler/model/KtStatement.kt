package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.CodeWritable

interface KtStatement : KtTyped, CodeWritable {
    override val type: KtType
        get() = KtType.Unit
}
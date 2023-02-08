package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.Codable

interface KtStatement : KtTyped, Codable {
    override val type: KtType
        get() = KtType.Unit
}
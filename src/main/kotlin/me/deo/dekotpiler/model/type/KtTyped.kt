package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.model.type.KtType

interface KtTyped {
    val type: KtType

    fun nullCheckedChain() =
        "${if (type.nullable) "?" else ""}."
}
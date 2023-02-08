package me.deo.dekotpiler.model

interface KtTyped {
    val type: KtType

    fun nullCheckedChain() =
        "${if (type.nullable) "?" else ""}."
}
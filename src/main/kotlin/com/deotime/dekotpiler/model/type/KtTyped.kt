package com.deotime.dekotpiler.model.type

interface KtTyped {
    val type: KtType

    fun nullCheckedChain() =
        "${if (type.nullable) "?" else ""}."
}
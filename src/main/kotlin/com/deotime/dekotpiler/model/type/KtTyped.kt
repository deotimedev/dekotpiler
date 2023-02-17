package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.model.type.KtType

interface KtTyped {
    val type: KtType

    fun nullCheckedChain() =
        "${if (type.nullable) "?" else ""}."
}
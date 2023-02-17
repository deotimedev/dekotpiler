package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.model.type.KtType
import kotlin.reflect.KTypeParameter

data class KtTypeParameter(
    val name: String,
    val upperbounds: List<KtType> = listOf(),
    val reified: Boolean = false
) {
    constructor(reflect: KTypeParameter) : this(
        reflect.name,
        reflect.upperBounds.map { KtType(it) },
        reflect.isReified
    )
}
package me.deo.dekotpiler.model

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
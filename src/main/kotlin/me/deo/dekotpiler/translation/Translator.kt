package me.deo.dekotpiler.translation

fun interface Translator<J, K> {
    fun Translation.translation(value: J): K
}
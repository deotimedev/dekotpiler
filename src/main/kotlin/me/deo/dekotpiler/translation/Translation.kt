package me.deo.dekotpiler.translation

import kotlin.reflect.KClass

interface Translation {
    fun <C : Any, K> translator(type: KClass<C>): Translator<C, K>
    fun <C, K> translate(value: C): K
}
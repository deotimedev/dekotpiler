package me.deo.dekotpiler.translation

import kotlin.reflect.KClass

interface Translator<J : Any, K> {
    val type: KClass<out J>
    fun J.proc(): Boolean = true
    fun Translation.translation(value: J): K
}
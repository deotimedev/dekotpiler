package me.deo.dekotpiler.translation

import me.deo.dekotpiler.util.Matchable
import kotlin.reflect.KClass

interface Translator<J : Any, K> : Matchable<J> {
    val type: KClass<out J>
    fun Translation.translation(value: J): K
}
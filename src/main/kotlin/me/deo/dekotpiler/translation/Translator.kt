package me.deo.dekotpiler.translation

import me.deo.dekotpiler.matching.Matcher
import kotlin.reflect.KClass

interface Translator<J : Any, K> : Matcher<J> {
    val type: KClass<out J>
    fun Translation.translation(value: J): K
    override fun J.match() = true
}
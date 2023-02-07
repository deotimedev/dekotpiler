package me.deo.dekotpiler.translation

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.provided.TypeMatcher
import kotlin.reflect.KClass

interface Translator<J : Any, K> : TypeMatcher<J> {
    val type: KClass<out J>
    fun Translation.translation(value: J): K
}
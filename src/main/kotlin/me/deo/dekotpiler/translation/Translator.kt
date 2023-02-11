package me.deo.dekotpiler.translation

import me.deo.dekotpiler.matching.ClassMatcher
import kotlin.reflect.KClass

interface Translator<J : Any, K> : ClassMatcher<J> {
    val type: KClass<out J>
    fun Translation.Session.translation(value: J): K

    // temp stuff
    override val clazz get() = type as KClass<J>
    override fun J.match() = true

}
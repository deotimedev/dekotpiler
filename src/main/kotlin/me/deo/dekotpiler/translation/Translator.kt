package me.deo.dekotpiler.translation

import me.deo.dekotpiler.matching.TypeMatcher
import kotlin.reflect.KClass

interface Translator<J : Any, K> : TypeMatcher<J> {
    val type: KClass<out J>
    fun Translation.translation(value: J): K

    // temp stuff
    override val clazz: Class<J>
        get() = type.java as Class<J>
    override fun J.match() = true

}
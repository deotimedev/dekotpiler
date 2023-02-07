package me.deo.dekotpiler.translation

import me.deo.dekotpiler.matching.Matcher
import kotlin.reflect.KClass

// todo better name for this
interface PostProcessor<T : Any> : Matcher<T> {
    val type: KClass<T>

    fun replace(value: T): Any = value.also { modify(it) }
    fun modify(value: T) = Unit
}
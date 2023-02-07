package me.deo.dekotpiler.processing

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.provided.TypeMatcher
import kotlin.reflect.KClass

interface Processor<T> : TypeMatcher<T> {
    val mode: Mode

    fun replace(value: T): Any? = value.also { modify(it) }
    fun modify(value: T) = Unit

    enum class Mode {
        Pre,
        Post
    }
}
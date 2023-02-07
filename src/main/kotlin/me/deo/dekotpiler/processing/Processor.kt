package me.deo.dekotpiler.processing

import me.deo.dekotpiler.matching.TypeMatcher

interface Processor<T> : TypeMatcher<T> {
    val mode: Mode

    fun replace(value: T): Any? = value.also { modify(it) }
    fun modify(value: T) = Unit

    enum class Mode {
        Pre,
        Post
    }
}
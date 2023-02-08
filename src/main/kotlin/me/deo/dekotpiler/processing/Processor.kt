package me.deo.dekotpiler.processing

import me.deo.dekotpiler.matching.ClassMatcher

interface Processor<T : Any> : ClassMatcher<T> {
    val mode: Mode

    fun replace(value: T): Any? = value.also { modify(it) }
    fun modify(value: T) = Unit

    enum class Mode {
        Pre,
        Post
    }
}
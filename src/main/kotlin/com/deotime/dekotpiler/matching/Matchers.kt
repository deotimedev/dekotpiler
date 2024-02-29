package com.deotime.dekotpiler.matching

object Matchers {
    fun <T> everything() = Matcher<T> { true }
    fun <T> nothing() = Matcher<T> { false }

    fun <T, V> value(reference: (T) -> V, vararg values: V) =
        Matcher<T> { reference(this) in values }

    fun <T, V> notNull(reference: (T) -> V) =
        !value(reference, null)

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> Matcher<T>.match(value: T) = value.match()

    infix fun <T> Matcher<T>.and(other: Matcher<T>) = Matcher<T> { match() && other.match(this) }
    infix fun <T> Matcher<T>.or(other: Matcher<T>) = Matcher<T> { match() || other.match(this) }
    infix fun <T> Matcher<T>.xor(other: Matcher<T>) = Matcher<T> { match() xor other.match(this) }
    operator fun <T> Matcher<T>.not() = Matcher<T> { !match() }
}
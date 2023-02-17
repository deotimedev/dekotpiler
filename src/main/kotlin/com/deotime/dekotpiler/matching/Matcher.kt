package com.deotime.dekotpiler.matching

fun interface Matcher<T> {
    fun T.match(): Boolean

    operator fun plus(other: Matcher<T>) = Matcher<T> {
        match() && with(other) { match() }
    }

    companion object {
        fun <T> Matcher<T>.match(value: T) = value.match()
    }
}
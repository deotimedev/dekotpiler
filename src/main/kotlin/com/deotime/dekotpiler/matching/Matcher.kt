package com.deotime.dekotpiler.matching

fun interface Matcher<T> {
    fun T.match(): Boolean

    operator fun not() = Matcher<T> { !match() }

    // would be cool if and and or were operator functions
    operator fun plus(other: Matcher<T>) =
        Matcher<T> { match() && with(other) { match() } }

}


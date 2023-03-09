package com.deotime.dekotpiler.matching

fun interface Matcher<T> : (T) -> Boolean {
    fun T.match(): Boolean
    override fun invoke(value: T) = value.match()
}


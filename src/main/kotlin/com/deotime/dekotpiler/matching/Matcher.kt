package com.deotime.dekotpiler.matching

fun interface Matcher<T> {
    fun T.match(): Boolean
}


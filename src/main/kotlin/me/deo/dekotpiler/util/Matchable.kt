package me.deo.dekotpiler.util

interface Matchable<T> {
    fun T.match(): Boolean = true
}
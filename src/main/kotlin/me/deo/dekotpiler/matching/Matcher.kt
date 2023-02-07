package me.deo.dekotpiler.matching

fun interface Matcher<T> {
    fun T.match(): Boolean

    operator fun <R : T> plus(other: Matcher<R>) = Matcher<R> {
        match() && with(other) { match() }
    }

    companion object {
        fun <T> Matcher<T>.match(value: T) = value.match()
    }
}
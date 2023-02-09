package me.deo.dekotpiler.util

inline fun <T> Iterable<T>.flatMapInclusive(closure: (T) -> Iterable<T>) =
    flatMap { closure(it) + it }

fun <T> MutableList<T>.update(index: Int, closure: (T) -> T) =
    getOrNull(index)?.let { old ->
        val new = closure(old)
        set(index, new)
        old to new
    }
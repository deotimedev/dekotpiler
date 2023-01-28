package me.deo.dekotpiler.util

fun <T> MutableList<T>.update(index: Int, closure: (T) -> T) =
    getOrNull(index)?.let { old ->
        val new = closure(old)
        set(index, new)
        old to new
    }
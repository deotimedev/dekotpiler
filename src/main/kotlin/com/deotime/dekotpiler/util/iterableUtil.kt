package com.deotime.dekotpiler.util

inline fun <T> Iterable<T>.flatMapInclusive(closure: (T) -> Iterable<T>) =
    flatMap { closure(it) + it }

fun <T> MutableList<T>.update(index: Int, closure: (T) -> T) =
    getOrNull(index)?.let { old ->
        val new = closure(old)
        set(index, new)
        old to new
    }

fun <T> MutableList<T>.updateLast(closure: (T) -> T) =
    getOrNull(lastIndex)?.let { old ->
        val new = closure(old)
        set(lastIndex, new)
        old to new
    }

inline fun <T, R> Iterable<T>.partitionNotNull(closure: (T) -> R?): Pair<List<R>, List<T>> {
    val a = mutableListOf<R>()
    val b = mutableListOf<T>()
    for (item in this) {
        closure(item)?.let { a += it } ?: run { b += item }
    }
    return a to b
}
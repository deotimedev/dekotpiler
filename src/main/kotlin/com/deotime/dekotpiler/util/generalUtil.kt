package com.deotime.dekotpiler.util

// i forgot what this does
inline fun <T> T?.gather(closure: (T) -> T?): List<T> {
    val self = this ?: return emptyList()
    return buildList {
        var item = self
        do {
            add(item)
        } while (closure(item)?.also { item = it } != null)
    }
}
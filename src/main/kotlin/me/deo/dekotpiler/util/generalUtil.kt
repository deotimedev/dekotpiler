@file:OptIn(ExperimentalContracts::class)

package me.deo.dekotpiler.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

inline fun <T> T?.gather(closure: (T) -> T?): List<T> {
    contract { callsInPlace(closure) }
    val self = this ?: return emptyList()
    return buildList {
        var item = self
        do {
            add(item)
        } while (closure(item)?.also { item = it } != null)
    }
}
package com.deotime.dekotpiler.util.backing

import java.util.IdentityHashMap

class StaticLifetimeMemory<R, T> {
    private val _delegate = IdentityHashMap<R, T>()

    fun memorize(ref: R, value: T) {
        _delegate[ref] = value
    }

    fun remember(ref: R, memory: R.() -> T) = _delegate.computeIfAbsent(ref, memory)
}
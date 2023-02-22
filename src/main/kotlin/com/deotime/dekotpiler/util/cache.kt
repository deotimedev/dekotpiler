package com.deotime.dekotpiler.util

import java.util.concurrent.ConcurrentHashMap

// Not thread safe!

class Cache<K, V> internal constructor(
    private val mapper: (K) -> V,
    private val delegate: MutableMap<K, V> = ConcurrentHashMap()
) : Map<K, V> by delegate {

    override fun get(key: K): V = delegate.computeIfAbsent(key, mapper)

}

fun <K, V> cache(mapper: (K) -> V) = Cache(mapper)
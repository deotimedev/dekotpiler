package com.deotime.dekotpiler.util

import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty

class Backing<R, T>(private val closure: R.() -> T) {

    private val memory = ConcurrentHashMap<Key<R>, T>()

    operator fun getValue(ref: R, prop: KProperty<*>): T = memory.computeIfAbsent(ref.key) { closure(ref) }
    operator fun setValue(ref: R, prop: KProperty<*>, value: T) {
        memory[ref.key] = value
    }

    private class Key<K>(value: K) : WeakReference<K>(value) {
        private val hash = System.identityHashCode(value)
        override fun equals(other: Any?) =
            this === other || (other as? Key<*>)?.get() == get()
        override fun hashCode() = hash
    }

    private inline val R.key get() = Key(this)
}

fun <R, T> backing(closure: R.() -> T) = Backing(closure)
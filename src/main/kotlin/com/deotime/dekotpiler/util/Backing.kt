package com.deotime.dekotpiler.util

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty

class Backing<R, T>(private val closure: R.() -> T) {

    private val memory = ConcurrentHashMap<Int, T>()

    operator fun getValue(ref: R, prop: KProperty<*>): T = memory.computeIfAbsent(ref.id) { closure(ref) }
    operator fun setValue(ref: R, prop: KProperty<*>, value: T) {
        memory[ref.id] = value
    }

    private inline val R.id get() = System.identityHashCode(this)
}

fun <R, T> backing(closure: R.() -> T) = Backing(closure)
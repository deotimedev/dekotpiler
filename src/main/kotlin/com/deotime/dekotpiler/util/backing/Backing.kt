package com.deotime.dekotpiler.util.backing

import kotlin.reflect.KProperty

class Backing<R, T>(
    private val closure: R.() -> T
) {

    private val memory = StaticLifetimeMemory<R, T>()

    operator fun getValue(ref: R, prop: KProperty<*>): T = memory.remember(ref, closure)
    operator fun setValue(ref: R, prop: KProperty<*>, value: T) {
        memory.memorize(ref, value)
    }
}

fun <R, T> backing(closure: R.() -> T) = Backing(closure)
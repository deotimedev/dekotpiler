@file:Suppress("UNCHECKED_CAST")

package com.deotime.dekotpiler.util

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import kotlin.reflect.KType
import kotlin.reflect.typeOf

interface View<out T> {
    fun get(): T
    fun <Key> unlock(type: KType, closure: (Unlocked<Key>) -> Unit)


    companion object {
        inline fun <reified Key> View<*>.unlock(noinline closure: (Unlocked<Key>) -> Unit) =
            unlock(typeOf<Key>(), closure)
    }

    interface Unlocked<out T> : View<T> {
        fun set(value: @UnsafeVariance T)

        companion object
    }

    class Simple<T>(
        private val _get: () -> T,
        private val _set: (T) -> Unit,
        private val type: KType
    ) : View<T> {
        constructor(prop: KMutableProperty0<T>) : this(prop::get, prop::set, prop.returnType)

        override fun get() = _get()
        override fun <Key> unlock(type: KType, closure: (Unlocked<Key>) -> Unit) {
            if (type >= this.type)
                closure(object : View<Key> by (this@Simple as View<Key>), Unlocked<Key> {
                    override fun set(value: @UnsafeVariance Key) =
                        (_set as (Key) -> Unit)(value)
                })
        }
    }
}

fun <T> views(vararg props: KMutableProperty0<T>) =
    props.map { View.Simple(it) }
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

    operator fun <Key> Unlocked.Companion.invoke(set: (Key) -> Unit): Unlocked<Key> =
        object : View<Key> by (this@View as View<Key>), Unlocked<Key> {
            override fun set(value: @UnsafeVariance Key) = set(value)
        }

    companion object {
        inline fun <reified Key> View<*>.unlock(noinline closure: (Unlocked<Key>) -> Unit) =
            unlock(typeOf<Key>(), closure)
    }

    interface Unlocked<out T> : View<T> {
        fun set(value: @UnsafeVariance T)

        companion object
    }

    class Simple<T>(
        private val prop: KMutableProperty0<@UnsafeVariance T>
    ) : View<T> {
        override fun get() = prop.get()
        override fun <Key> unlock(type: KType, closure: (Unlocked<Key>) -> Unit) {
            if (type >= prop.returnType)
                closure(Unlocked((prop as KMutableProperty0<Key>)::set))
        }
    }
}
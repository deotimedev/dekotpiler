package com.deotime.dekotpiler.matching

import kotlin.reflect.KClass

interface ValueMatcher<T : Any, V> : Matcher<T> {

    val reference: (T) -> V
    val value: V

    class Simple<T : Any, V>(
        override val reference: (T) -> V,
        override val value: V
    ) : ValueMatcher<T, V> {
        override fun T.match() = reference(this) == value
    }

    companion object {
        inline operator fun <reified T : Any, V> invoke(noinline reference: (T) -> V, value: V): ValueMatcher<T, V> = Simple(reference, value)
    }
}
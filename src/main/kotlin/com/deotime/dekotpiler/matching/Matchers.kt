@file:Suppress("NOTHING_TO_INLINE")
package com.deotime.dekotpiler.matching

import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke

object Matchers {

    inline fun <T> everything() = Matcher<T> { true }
    inline fun <T> nothing() = Matcher<T> { false }

    inline fun <T, V> value(crossinline reference: (T) -> V, vararg values: V) =
        Matcher<T> { reference(this) in values }

    inline fun <T, V> notNull(crossinline reference: (T) -> V) =
        !value(reference, null)

    inline fun <reified T> staticFunction(vararg names: String) =
        Matcher<KtStaticInvoke> {
            function.enclosing.qualifiedName == T::class.qualifiedName && name in names
        }

    inline fun <T> Matcher<T>.match(value: T) = value.match()

    private fun <T> Matcher<T>.transform(other: Matcher<T>, boolOp: (Boolean, Boolean) -> Boolean) =
        Matcher<T> { boolOp(match(), other.match(this)) }

    private inline fun <T> Matcher<T>.transform(crossinline boolOp: (Boolean) -> Boolean) =
        Matcher<T> { boolOp(match()) }

    infix fun <T> Matcher<T>.and(other: Matcher<T>) = Matcher<T> { match() && other.match(this) }
    infix fun <T> Matcher<T>.or(other: Matcher<T>) = Matcher<T> { match() || other.match(this) }

    infix fun <T> Matcher<T>.xor(other: Matcher<T>) = transform(other, Boolean::xor)
    operator fun <T> Matcher<T>.not() = transform(Boolean::not)
}
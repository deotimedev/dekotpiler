package com.deotime.dekotpiler.matching

import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke

object Matchers {

    fun <T> everything() = Matcher<T> { true }
    fun <T> nothing() = Matcher<T> { false }

    fun <T, V> value(reference: (T) -> V, vararg values: V) =
        Matcher<T> { reference(this) in values }

    fun <T, V> notNull(reference: (T) -> V) = !value(reference, null)

    inline fun <reified T> staticFunction(vararg names: String) =
        Matcher<KtStaticInvoke> {
            function.enclosing.qualifiedName == T::class.qualifiedName && name in names
        }

    fun <T> Matcher<T>.match(value: T) = value.match()

    private fun <T> Matcher<T>.transform(other: Matcher<T>, boolOp: (Boolean, Boolean) -> Boolean) =
        Matcher<T> { boolOp(match(), other.match(this)) }

    private fun <T> Matcher<T>.transform(boolOp: (Boolean) -> Boolean) =
        Matcher<T> { boolOp(match()) }

    infix fun <T> Matcher<T>.and(other: Matcher<T>) = transform(other) { a, b -> a && b }
    infix fun <T> Matcher<T>.or(other: Matcher<T>) = transform(other) { a, b -> a || b }

    infix fun <T> Matcher<T>.xor(other: Matcher<T>) = transform(other, Boolean::xor)
    operator fun <T> Matcher<T>.not() = transform(Boolean::not)
}
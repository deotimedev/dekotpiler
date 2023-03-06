package com.deotime.dekotpiler.matching

import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke

object Matchers {

    fun <T, V> value(reference: (T) -> V, vararg values: V) =
        Matcher<T> { reference(this) in values }

    fun <T, V> notNull(reference: (T) -> V) = !value(reference, null)

    inline fun <reified T> staticFunction(vararg names: String) =
        Matcher<KtStaticInvoke> {
            function.enclosing.qualifiedName == T::class.qualifiedName && name in names
        }

    fun <T> Matcher<T>.match(value: T) = value.match()
}
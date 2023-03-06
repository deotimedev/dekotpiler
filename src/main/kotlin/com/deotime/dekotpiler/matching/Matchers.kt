package com.deotime.dekotpiler.matching

import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke

object Matchers {

    fun <T : Any, V> value(reference: (T) -> V, value: V) =
        Matcher<T> { reference(this) == value }

    inline fun <reified T> staticFunction(vararg names: String) =
        Matcher<KtStaticInvoke> {
            function.enclosing.qualifiedName == T::class.qualifiedName && name in names
        }

    fun <T> Matcher<T>.match(value: T) = value.match()
}
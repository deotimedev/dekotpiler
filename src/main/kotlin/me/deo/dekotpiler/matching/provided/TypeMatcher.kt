package me.deo.dekotpiler.matching.provided

import me.deo.dekotpiler.matching.Matcher

interface TypeMatcher<T> : Matcher<Any> {

    val clazz: Class<T>

    class Simple<T>(override val clazz: Class<T>) : TypeMatcher<T> {
        override fun Any.match() =
            this::class.java == clazz
    }

    companion object {
        inline operator fun <reified T> invoke() = TypeMatcher.Simple(T::class.java)
    }
}
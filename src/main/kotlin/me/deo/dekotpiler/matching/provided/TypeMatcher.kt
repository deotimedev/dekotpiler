package me.deo.dekotpiler.matching.provided

import me.deo.dekotpiler.matching.Matcher

// TODO have this impl in translation / processingg
class TypeMatcher<T>(
    val clazz: Class<T>
) : Matcher<Any> {

    override fun Any.match() =
        this::class.java == clazz

    companion object {
        inline operator fun <reified T> invoke() = TypeMatcher(T::class.java)
    }
}
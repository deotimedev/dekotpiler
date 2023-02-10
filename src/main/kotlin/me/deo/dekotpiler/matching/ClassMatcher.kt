package me.deo.dekotpiler.matching

import kotlin.reflect.KClass

// FIXME
interface ClassMatcher<T : Any> : Matcher<T> {

    val clazz: KClass<T>

    class Simple<T : Any>(override val clazz: KClass<T>) : ClassMatcher<T> {
        override fun T.match() =
            this::class.java == clazz.java
    }

    override fun plus(other: Matcher<T>): ClassMatcher<T> = object : ClassMatcher<T> {
        override val clazz = this@ClassMatcher.clazz
        override fun T.match() =
            with(this@ClassMatcher) { match() } && with(other) { match() }
    }

    companion object {
        inline operator fun <reified T : Any> invoke() = Simple(T::class)
    }
}
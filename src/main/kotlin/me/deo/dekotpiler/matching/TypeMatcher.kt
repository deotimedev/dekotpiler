package me.deo.dekotpiler.matching

interface TypeMatcher<T> : Matcher<T> {

    val clazz: Class<T>

    class Simple<T>(override val clazz: Class<T>) : TypeMatcher<T> {
        override fun T.match() =
            this?.let { it::class.java } == clazz
    }

    override fun plus(other: Matcher<T>): TypeMatcher<T> = object : TypeMatcher<T> {
        override val clazz = this@TypeMatcher.clazz
        override fun T.match() =
            with(this@TypeMatcher) { match() } && with(other) { match() }
    }

    companion object {
        inline operator fun <reified T : Any> invoke() = Simple(T::class.java)
    }
}
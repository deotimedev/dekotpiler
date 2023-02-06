package me.deo.dekotpiler.util

// change to value class when generics work
class Single<T>(val value: T) : Iterable<T> {
    override fun iterator() = object : AbstractIterator<T>() {
        private var used = false
        override fun computeNext() {
            if (used) done()
            else {
                setNext(value)
                used = true
            }

        }
    }
}

fun <T> singleOf(value: T) = Single(value)
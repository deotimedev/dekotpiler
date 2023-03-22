package com.deotime.dekotpiler.util

@JvmInline
value class Single<T>(val value: T) : Collection<T> {

    override val size get() = 1
    override fun isEmpty() = false

    override fun containsAll(elements: Collection<T>) =
        elements.isEmpty() || (elements.size == 1 && elements.first() == value)

    override fun contains(element: T) = element == value
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
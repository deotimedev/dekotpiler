package com.deotime.dekotpiler.util

class Single<T>(val value: T) : AbstractList<T>() {

    override val size = 1

    override fun get(index: Int) =
        if (index == 0) value else throw IndexOutOfBoundsException(index)

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
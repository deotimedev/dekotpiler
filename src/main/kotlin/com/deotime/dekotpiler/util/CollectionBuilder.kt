package com.deotime.dekotpiler.util

interface CollectionBuilder<T> {
    operator fun T.unaryPlus()
    operator fun T.unaryMinus()

    operator fun Iterable<T>.unaryPlus() = forEach { it.unaryPlus() }
    operator fun Iterable<T>.unaryMinus() = forEach { it.unaryMinus() }
}

@PublishedApi
internal class CollectionBuilderImpl<A, T>(
    private val collection: MutableCollection<A>,
    private val transform: (T) -> A
) : CollectionBuilder<T> {
    override fun T.unaryPlus() {
        collection += transform(this)
    }

    override fun T.unaryMinus() {
        collection -= transform(this)
    }
}

typealias CollectionAssembler<T> = CollectionBuilder<T>.() -> Unit

fun <T> CollectionAssembler<T>.assemble(): List<T> =
    mutableListOf<T>().apply { buildCollectionTo(this, this@assemble) }

inline fun <T, C : MutableCollection<T>> buildCollectionTo(col: C, assembler: CollectionAssembler<T>) =
    buildCollectionTo(col, { it }, assembler)

inline fun <A, T, C : MutableCollection<A>> buildCollectionTo(
    col: C,
    noinline transform: (T) -> A,
    assembler: CollectionAssembler<T>
) {
    CollectionBuilderImpl(col, transform).assembler()
}
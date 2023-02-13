@file:Suppress("NOTHING_TO_INLINE")

package me.deo.dekotpiler.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface Either<out L, out R> {
    val value: Any?

    data class Left<L>(override val value: L) : Either<L, Nothing>
    data class Right<R>(override val value: R) : Either<Nothing, R>

}

// todo will change for .right if you wanted to do that for ome reasons
class LeftCheck<L, R, U>(
    private val either: Either<L, R>,
    private val left: (L) -> U
) {
    fun right(closure: (R) -> U): U =
        when (either) {
            is Either.Left<L> -> left(either.value)
            is Either.Right<R> -> closure(either.value)
        }
}

inline fun <L> left(value: L) = Either.Left(value)
inline fun <R> right(value: R) = Either.Right(value)

@OptIn(ExperimentalContracts::class)
inline fun <L, R> Either<L, R>.isLeft(): Boolean {
    contract { returns(true) implies (this@isLeft is Either.Left<L>) }
    return this is Either.Left<L>
}

@OptIn(ExperimentalContracts::class)
inline fun <L, R> Either<L, R>.isRight(): Boolean {
    contract { returns(true) implies (this@isRight is Either.Right<R>) }
    return this is Either.Right<R>
}

fun <L, R, U> Either<L, R>.left(closure: (L) -> U) = LeftCheck(this, closure)
@file:Suppress("NOTHING_TO_INLINE")

package me.deo.dekotpiler.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface Either<out L, out R> {
    val value: Any?

    data class Left<L>(override val value: L) : Either<L, Nothing>
    data class Right<R>(override val value: R) : Either<Nothing, R>

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

@DslMarker
annotation class MatcherDsl

class Match<L, R, U>(@PublishedApi internal val either: Either<L, R>) {

    @PublishedApi
    internal var value: Any? = Uninitialized

    @MatcherDsl
    val left = Left
    @MatcherDsl
    val right = Right

    inline operator fun Left.compareTo(closure: (L) -> U): Int {
        if (either.isLeft()) value = closure(either.value)
        return 0
    }

    inline operator fun Right.compareTo(closure: (R) -> U): Int {
        if (either.isRight()) value = closure(either.value)
        return 0
    }

    fun match() = value as U

    private object Uninitialized
    object Left
    object Right

}

@MatcherDsl
inline fun <L, R, U> match(either: Either<L, R>, matcher: Match<L, R, U>.() -> Unit): U = Match<L, R, U>(either).apply(matcher).match()
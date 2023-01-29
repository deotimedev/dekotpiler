package me.deo.dekotpiler

import kotlin.random.Random

object TestHelper {

    fun maybe(): String? = if (Random.nextBoolean()) "Hello" else null

    fun notInlinedClosure(closure: () -> Unit) {
        closure()
    }

    inline fun inlinedClosure(closure: () -> Unit) {
        closure()
    }

    sealed interface Algebraic {
        data class Right(val value: String) : Algebraic
        object Wrong : Algebraic

        companion object {
            fun random() = if (Random.nextBoolean()) Right("Okay") else Wrong
        }
    }
}
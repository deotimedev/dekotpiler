package me.deo.dekotpiler

import kotlin.random.Random

object TestHelper {

    fun maybe(): String? = if (Random.nextBoolean()) "Hello" else null

    fun notInlinedClosure(closure: () -> Unit) {
        closure()
    }

    inline fun inlinedClosure(closure: () -> Unit) {
        println("start of inline closure")
        closure()
        println("end of inline closure")
    }

    sealed interface Algebraic {
        data class Right(val value: String) : Algebraic
        object Wrong : Algebraic

        companion object {
            fun random() = if (Random.nextBoolean()) Right("Okay") else Wrong
        }
    }

    interface Parent {
        companion object {
            val child: Parent = TestHelper.Child
        }
    }
    object Child : Parent
}
package com.deotime.dekotpiler

import kotlin.random.Random

object TestHelper {

    val someProp = 534
    fun obj() = Opp()
    fun maybe(): String? = if (Random.nextBoolean()) "Hello" else null

    fun randInt() = Random.nextInt()
    var getterSetterValue
        get() = "Got"
        set(value) {
            println("Sot")
        }

    fun notInlinedClosure(closure: () -> Unit) {
        closure()
    }

    inline fun <reified T> reified() {
        println(T::class.java)
    }

    fun generic(): List<String> = listOf("yes", "opk")

    inline fun inlinedClosure(closure: () -> Unit) {
        println("start of inline closure")
        closure()
        println("end of inline closure")
    }

    class Opp {
        val tset = true
        operator fun unaryPlus() = "Hoka"
        operator fun plus(num: Int) = "Sss"
        operator fun set(amount: Int, name: String, alive: Boolean) {
            println("idk")
        }
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
            val child: Parent = Child
        }
    }

    object Child : Parent

    object Opable {
        operator fun unaryPlus() {

        }
    }

    fun bool(value: Boolean) = value

    enum class SomeEnum {
        Thing,
        AnotherThing,
        Crazy,
        Stack
    }
}

fun String.stringExtension() {

}

var String.extensionValue
    get() = "Hi"
    set(value) = Unit
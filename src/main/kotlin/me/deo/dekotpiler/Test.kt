package me.deo.dekotpiler

import kotlin.random.Random

class Test {
    fun test() {
        val num = Random.nextInt()
        when (num) {
            0 -> println("Number is false")
            1, 100 -> println("Number is true")
            else -> println("Number is a troolean+")
        }
    }
}
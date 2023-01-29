package me.deo.dekotpiler

import kotlin.random.Random

class Test {
    fun test() {
        val num = Random.nextInt()
        val display = if (num > 100) "Big number!" else "Small number"
        println(display)
    }
}
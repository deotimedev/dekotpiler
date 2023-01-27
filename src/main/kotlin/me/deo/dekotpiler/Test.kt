package me.deo.dekotpiler

import kotlin.random.Random

fun test() {
    println("Hello everyone!")
    var i = 0
    i = Random.nextInt()
    println("Rand: $i")
    for (thing in 0..10) {
        println(thing)
    }
}
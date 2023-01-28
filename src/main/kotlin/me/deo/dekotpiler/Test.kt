package me.deo.dekotpiler

import kotlin.random.Random

fun test() {
    println("Hello everyone!")
    val rand = Random.nextInt(5, 10)
    println("Rand: $rand")
    var count = 0
    for (thing in 0..rand) {
        count += thing
        println(thing)
    }
    val message = if (count % 2 == 0) "Even" else "Odd"
    error(message)
}
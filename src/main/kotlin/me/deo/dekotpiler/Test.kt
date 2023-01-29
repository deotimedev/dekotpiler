package me.deo.dekotpiler

import java.io.File
import kotlin.random.Random

class Test {
    fun test() {
        try {
            println("Hello")
        } catch (exception: NullPointerException) {
            println("good golly")
        }
    }
}
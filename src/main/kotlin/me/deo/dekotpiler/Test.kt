package me.deo.dekotpiler

import java.io.File
import java.util.UUID
import kotlin.random.Random
import kotlin.reflect.typeOf

class Test {
    fun test() {
        val thing = "Hellok ${Random.nextInt()}"
        "Okay".extensionValue
        "why".extensionValue = "okay"
        if (TestHelper.bool(true) && TestHelper.bool(false)) {
            val test = 55
            println("HEllo $test")
        }
    }
}
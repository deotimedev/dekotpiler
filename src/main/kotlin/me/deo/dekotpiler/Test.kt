package me.deo.dekotpiler

import java.io.File
import kotlin.random.Random

class Test {
    fun test() {
        val usefulthing = TestHelper.Parent.child as? TestHelper.Child
        println(usefulthing)
    }
}
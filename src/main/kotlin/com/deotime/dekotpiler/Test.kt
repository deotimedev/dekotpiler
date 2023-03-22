package com.deotime.dekotpiler

import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.typeOf

class Test {


    @OptIn(ExperimentalStdlibApi::class)
    fun test() {


        "Yes!".stringExtension()
        println(TestHelper.SomeEnum.entries)
        println(1..10)
        println(typeOf<Array<String>>())
        val stringBuilder = StringBuilder()
        stringBuilder.append("yess").append(555)
        println(stringBuilder.toString())
        TestHelper.getterSetterValue = "555"
        if (TestHelper.obj().tset) {
            repeat(25) {
                println("Num: ${Random.nextInt(5..10)}")
            }
        }

        if (!Random.nextBoolean()) test()
        val clazz = Test::class

        println(clazz.isData)
        val helper = TestHelper.Opp()
        helper[55, "yes"] = true
        val thing = "Hellok ${Random.nextInt()}!!! Great"
        "Okay".extensionValue
        "why".extensionValue = "okay"
        println(TestHelper.maybe())
        while (Random.nextBoolean()) {
            if (TestHelper.bool(true) && TestHelper.bool(false) || TestHelper.bool(true)) {
                val test = 55
                println("HEllo $test")
            }
        }
        println(charArrayOf('a', 'b', 'c'))
        TestHelper.reified<String>()
        println("yes")
    }

}

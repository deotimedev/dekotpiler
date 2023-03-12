package com.deotime.dekotpiler

import com.deotime.javatest.JavaTestHelper
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.typeOf
import kotlin.streams.toList

class Test {

    @OptIn(ExperimentalStdlibApi::class)
    fun test() {

        var test by Delegates.notNull<Int>()
        test = 55
        println("Test is ${test}")
//        "Yes!".stringExtension()
//        println(TestHelper.SomeEnum.entries)
//        println(1..10)
//        println(typeOf<Array<String>>())
//        val stringBuilder = StringBuilder()
//        stringBuilder.append("yess").append(555)
//        println(stringBuilder.toString())
//        TestHelper.getterSetterValue = "555"
//        if (TestHelper.obj().tset) {
//            repeat(25) {
//                println("Num: ${Random.nextInt(5..10)}")
//            }
//        }
//
//        if (!Random.nextBoolean()) test()
//        val clazz = Test::class
//
//        println(clazz.isData)
//        val helper = TestHelper.Opp()
//        helper[55, "yes"] = true
//        val thing = "Hellok ${Random.nextInt()}!!! Great"
//        "Okay".extensionValue
//        "why".extensionValue = "okay"
//        println(TestHelper.maybe())
//        while (Random.nextBoolean()) {
//            if (TestHelper.bool(true) && TestHelper.bool(false) || TestHelper.bool(true)) {
//                val test = 55
//                println("HEllo $test")
//            }
//        }
//        println(charArrayOf('a', 'b', 'c'))
//        TestHelper.reified<String>()
//        println("yes")
    }

}


//    @OptIn(ExperimentalStdlibApi::class)
//    fun test() {
//        "Yes!".stringExtension()
//        println(TestHelper.SomeEnum.entries)
//        println(1..10)
//        println(typeOf<Array<String>>())
//        val stringBuilder = StringBuilder()
//        stringBuilder.append("yess").append(555)
//        println(stringBuilder.toString())
//        TestHelper.getterSetterValue = "555"
//        if (TestHelper.obj().tset) {
//            repeat(25) {
//                println("Num: ${Random.nextInt(5..10)}")
//            }
//        }
//
//        if (Random.nextBoolean()) test()
//        val clazz = Test::class
//
//        println(clazz.isData)
//        val method = Test::test as Function<Int, String>
//        println(method.apply(55))
//        val helper = TestHelper.Opp()
//        helper[55, "yes"] = true
//        val thing = "Hellok ${Random.nextInt()}!!! Great"
//        "Okay".extensionValue
//        "why".extensionValue = "okay"
//        println(TestHelper.maybe())
//        while (Random.nextBoolean()) {
//            if (TestHelper.bool(true) && TestHelper.bool(false) || TestHelper.bool(true)) {
//                val test = 55
//                println("HEllo $test")
//            }
//        }
//        println(charArrayOf('a', 'b', 'c'))
//        TestHelper.reified<String>()
//        println("yes")
//    }


//// testing stuff
//
//interface Lens<T> {
//    fun get(): T
//    fun set(new: T)
//
//    data class Simple<T>(
//        private val _get: () -> T,
//        private val _set: (T) -> Unit
//    ) : Lens<T> {
//        override fun get() = _get()
//        override fun set(new: T) = _set(new)
//    }
//
//    companion object {
//        fun <T> Lens<T>.update(closure: (T) -> T) {
//            set(closure(get()))
//        }
//    }
//}
//
//class LensicalList<T> : AbstractMutableList<T>() {
//    private val delegate = mutableListOf<Lens<T>>()
//
//    fun lenses() = delegate
//
//    override val size = delegate.size
//
//    override fun add(index: Int, element: T) =
//        delegate.add(index, lens(element))
//
//    override fun removeAt(index: Int) =
//        delegate.removeAt(index).get()
//
//    override fun set(index: Int, element: T) =
//        delegate.set(index, lens(element)).get()
//
//    override fun get(index: Int) =
//        delegate[index].get()
//}
//
//fun <T> lensicalListOf(vararg items: T) =
//    LensicalList<T>().apply { addAll(items) }
//
//fun <T> lens(value: T) = object : Lens<T> {
//    private var backing: T = value
//    override fun get() = backing
//    override fun set(new: T) {
//        backing = new
//    }
//}
//
//fun <T> KMutableProperty0<T>.toLens(): Lens<T> = Lens.Simple(::get, ::set)
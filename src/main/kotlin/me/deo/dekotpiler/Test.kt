package me.deo.dekotpiler

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.Matcher.Companion.match
import org.jetbrains.annotations.NotNull
import java.util.Base64
import java.util.function.Function
import java.util.function.Supplier
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

class Test {
    fun test(test: String) {
        println(1..10)
        println(test.length)
        println(typeOf<String.(Int, Char) -> Boolean>())
        val stringBuilder = StringBuilder()
        stringBuilder.append("yess").append(555)
        println(stringBuilder.toString())
        TestHelper.getterSetterValue = "555"
        if (TestHelper.obj().tset) {
            repeat(25) {
                println("Num: ${Random.nextInt(5..10)}")
            }
        }

        if (Random.nextBoolean()) test("T")
        val clazz = Test::class

        println(clazz.isData)
        val method = Test::test as Function<Int, String>
        println(method.apply(55))
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
    }

}

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
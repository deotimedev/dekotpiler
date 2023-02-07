package me.deo.dekotpiler

import me.deo.dekotpiler.util.singleOf
import kotlin.random.Random
import kotlin.reflect.KMutableProperty0

class Test {
    fun test() {
        val clazz = Test::class
        println(clazz.isData)
        val thing = "Hellok ${Random.nextInt()}!!! Great"
        "Okay".extensionValue
        "why".extensionValue = "okay"
        while (Random.nextBoolean()) {
            if (TestHelper.bool(true) && TestHelper.bool(false)) {
                val test = 55
                println("HEllo $test")
            }
        }
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
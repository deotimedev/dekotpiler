package me.deo.dekotpiler

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.Matcher.Companion.match
import org.jetbrains.annotations.NotNull
import java.util.Base64
import java.util.function.Function
import java.util.function.Supplier
import kotlin.concurrent.thread
import kotlin.math.absoluteValue
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.reflect.KClass
import kotlin.reflect.typeOf
import kotlin.streams.toList
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class Test {
    fun test() {
        val test = TestHelper.obj()
        println(test::class.java)
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
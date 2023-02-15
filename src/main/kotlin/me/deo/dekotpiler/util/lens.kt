package me.deo.dekotpiler.util

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

// todo will probably change this later but just want to get the base impl in for now

typealias ViewableList<T, F> = List<Either<View<T, out F>, MutableList<View<T, out F>>>>

class View<T, F>(
    val holder: T,
    val get: () -> F,
    val set: (F) -> Unit
)

fun <T, F> T.view(focus: KProperty0<out F>) = View(this, focus.getter,
    ((focus as? KMutableProperty0<out F>)?.setter ?: { error("Setted the unsettable") }) as (F) -> Unit) // fixme
fun <T, F> T.views(vararg focuses: KProperty0<*>): ViewableList<T, F> =
    focuses.map { field ->
        if ((MutableList::class.java.isAssignableFrom((field.returnType.classifier as KClass<*>).java)))
            right(view(field))
        else left(view(field))
    } as ViewableList<T, F>


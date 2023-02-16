package me.deo.dekotpiler.util

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0

// todo will probably change this later but just want to get the base impl in for now

// this is likely to be replaceable with List<View<T, Either<out F, MutableList<out F>>>
typealias ViewableList<T, F> = List<Either<View<T, out F>, View<T, MutableList<out F>>>>

class View<T, F>(
    val holder: T,
    val get: () -> F,
    val set: (F) -> Unit
)

fun <T, F> T.view(focus: KProperty0<out F>) = View(this, focus.getter,
    ((focus as? KMutableProperty0<out F>)?.setter ?: { error("Setted the unsettable") }) as (F) -> Unit) // fixme
fun <T, F> T.views(vararg focuses: KProperty0<*>): ViewableList<T, F> =
    focuses.map { field ->
        if ((MutableList::class.java.isAssignableFrom((field.returnType.classifier as KClass<*>).java))) right(view(field))
        else left(view(field))
    } as ViewableList<T, F>

inline fun <T, reified L, reified R, S> T.viewEither(prop: KMutableProperty0<Either<L, R>>) = View<T, S>(
    this,
    {
        prop.get().unsafeUnwrap()
    },
    {
        prop.set(
            if (it is L) left(it)
            else if (it is R) right(it)
            else error("Wrong either type")
        )
    }
)
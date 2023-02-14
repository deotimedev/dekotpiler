package me.deo.dekotpiler.util

import me.deo.dekotpiler.model.KtExpression
import kotlin.reflect.KMutableProperty0
class Lens<T, F>(
    val holder: T,
    val get: () -> F,
    val set: (F) -> Unit
)

fun <T, F> T.lens(focus: KMutableProperty0<out F>) = Lens(this, focus.getter,
    focus.setter as (F) -> Unit) // fixme
fun <T, F> T.lenses(vararg focuses: KMutableProperty0<out F>) = focuses.map(::lens)


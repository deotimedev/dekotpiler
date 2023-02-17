package com.deotime.dekotpiler.util

import kotlinx.coroutines.Deferred
import kotlin.reflect.KProperty

@Suppress("UNSUPPORTED")
internal suspend operator fun <T> Deferred<T>.getValue(ref: Any?, prop: KProperty<*>?) = await()
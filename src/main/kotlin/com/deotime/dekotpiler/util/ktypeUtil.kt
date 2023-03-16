package com.deotime.dekotpiler.util

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType

operator fun KClass<*>.compareTo(other: KClass<*>) =
    starProjectedType.compareTo(other.starProjectedType)

operator fun KType.compareTo(other: KType) =
    when {
        isSubtypeOf(other) -> 1
        this == other -> 0
        else -> -1
    }
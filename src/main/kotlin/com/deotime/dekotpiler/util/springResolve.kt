package com.deotime.dekotpiler.util

import org.springframework.context.ApplicationContext
import org.springframework.core.ResolvableType
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

// TOOD this will be removed once spring is no longer in use
@PublishedApi
internal lateinit var context: ApplicationContext

inline fun <reified T : Any> resolve(): Lazy<T> =
    lazy(LazyThreadSafetyMode.PUBLICATION) {
        context.getBeanProvider<T>(ResolvableType.forType(typeOf<T>().javaType)).getObject()
    }

inline fun <reified T : Any> resolveAll(): Lazy<List<T>> =
    lazy(LazyThreadSafetyMode.PUBLICATION) {
        val type = ResolvableType.forType(typeOf<T>().javaType)
        context.getBeanNamesForType(type)
            .map(context::getBean)
            .filterIsInstance<T>()
    }
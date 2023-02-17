package com.deotime.dekotpiler.util

typealias MutableQueue<T> = ArrayDeque<T>
fun <T> mutableQueueOf(vararg items: T) = MutableQueue(items.toList())

fun <T> MutableQueue<T>.peek() = firstOrNull()
fun <T> MutableQueue<T>.poll() = removeFirstOrNull()
fun <T> MutableQueue<T>.push(value: T) = addLast(value)
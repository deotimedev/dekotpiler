package me.deo.dekotpiler.processing

fun interface Visitor<C : ProcessingContext> {
    fun C.visit()
}
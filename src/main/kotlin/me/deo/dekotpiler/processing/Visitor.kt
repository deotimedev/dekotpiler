package me.deo.dekotpiler.processing

import com.github.javaparser.ast.CompilationUnit

fun interface Visitor<C : Visitor.Context> {
    fun C.visit()

    interface Context {
        val context: CompilationUnit

        operator fun <T> get(key: Key<T>): T?
        operator fun <T> get(key: Key.Defaulted<T>): T = (get(key) ?: with(key) { default() })
        operator fun <T> set(key: Key<T>, value: T)

        interface Factory {
            fun create(context: CompilationUnit): Context
        }

        interface Key<T> {
            interface Defaulted<T> : Key<T> {
                fun Context.default(): T
            }
        }
    }
}
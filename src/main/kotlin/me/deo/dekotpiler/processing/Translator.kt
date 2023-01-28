package me.deo.dekotpiler.processing

import com.github.javaparser.ast.CompilationUnit

fun interface Translator<T> {
    fun Context.translate(value: T): String

    interface Context : TranslationHelper {
        val context: CompilationUnit

        operator fun <T> get(key: Key<T>): T?
        operator fun <T> get(key: Key.Defaulted<T>): T
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
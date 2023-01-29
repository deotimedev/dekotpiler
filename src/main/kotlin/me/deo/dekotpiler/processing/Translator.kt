package me.deo.dekotpiler.processing

import com.github.javaparser.ast.CompilationUnit

fun interface Translator<T> {
    fun Context.translate(value: T): Code

    // TODO this NEEDS to be scoped to current statement
    interface Context : TranslationHelper {
        val code: MutableList<Code>
        val currentIndex get() = code.size + 1
        val context: CompilationUnit

        operator fun <T> get(hint: Hint<T>): T?
        operator fun <T> get(hint: Hint.Defaulted<T>): T
        operator fun <T> set(hint: Hint<T>, value: T)
        fun <T> update(hint: Hint<T>, closure: (T) -> T)

        interface Factory {
            fun create(context: CompilationUnit): Context
        }

        interface Hint<T> {
            interface Defaulted<T> : Hint<T> {
                fun Context.default(): T
            }
        }
    }
}
package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.CompilationUnit
import me.deo.dekotpiler.processing.TranslationHelper
import me.deo.dekotpiler.processing.Translator
import java.util.concurrent.ConcurrentHashMap

class StandardVisitorContext(
    translationHelper: TranslationHelper,
    override val context: CompilationUnit
) : Translator.Context, TranslationHelper by translationHelper {
    private val state = ConcurrentHashMap<Translator.Context.Key<Any>, Any>()

    override fun <T> get(key: Translator.Context.Key<T>) = unsafeState<T>()[key]
    override fun <T> get(key: Translator.Context.Key.Defaulted<T>): T = (get(key) ?: with(key) { default() })
    override fun <T> set(key: Translator.Context.Key<T>, value: T) {
        unsafeState<T>()[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> unsafeState() = (state as MutableMap<Translator.Context.Key<T>, T>)
}

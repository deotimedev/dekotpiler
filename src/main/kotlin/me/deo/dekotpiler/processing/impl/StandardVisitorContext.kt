package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.CompilationUnit
import me.deo.dekotpiler.hints.UninitializedVariableHint.default
import me.deo.dekotpiler.processing.Code
import me.deo.dekotpiler.processing.TranslationHelper
import me.deo.dekotpiler.processing.Translator
import java.util.concurrent.ConcurrentHashMap

class StandardVisitorContext(
    translationHelper: TranslationHelper,
    override val context: CompilationUnit,
    override val code: MutableList<Code> = mutableListOf()
) : Translator.Context, TranslationHelper by translationHelper {
    private val state = ConcurrentHashMap<Translator.Context.Hint<Any>, Any>()

    private fun <T> _get(hint: Translator.Context.Hint<T>) =
        unsafeState<T>()[hint] ?: (hint as? Translator.Context.Hint.Defaulted<T>)?.run { default().also { set(hint, it) } }
    override fun <T> get(hint: Translator.Context.Hint<T>) = _get(hint)
    override fun <T> get(hint: Translator.Context.Hint.Defaulted<T>): T = _get(hint)!!
    override fun <T> set(hint: Translator.Context.Hint<T>, value: T) {
        unsafeState<T>()[hint] = value
    }

    override fun <T> update(hint: Translator.Context.Hint<T>, closure: (T) -> T) {
        get(hint)?.let { set(hint, closure(it)) }
    }

    fun scope() {
        TODO()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> unsafeState() = (state as MutableMap<Translator.Context.Hint<T>, T>)
}

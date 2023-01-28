package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.CompilationUnit
import me.deo.dekotpiler.processing.Visitor
import java.util.concurrent.ConcurrentHashMap

data class StandardVisitorContext(
    override val context: CompilationUnit
) : Visitor.Context {
    private val state = ConcurrentHashMap<Visitor.Context.Key<Any>, Any>()

    override fun <T> get(key: Visitor.Context.Key<T>) = unsafeState<T>()[key]
    override fun <T> set(key: Visitor.Context.Key<T>, value: T) {
        unsafeState<T>()[key] = value
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> unsafeState() = (state as MutableMap<Visitor.Context.Key<T>, T>)
}

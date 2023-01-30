package me.deo.dekotpiler.translation

import me.deo.dekotpiler.util.update

inline fun codeWriter(closure: Code.() -> Unit) =
    Code().apply(closure)

fun emptyCode() = Code()
fun codeOf(vararg values: String) = codeWriter { values.forEach { +it } }
class Code(
    private val lines: MutableList<String> = mutableListOf(""),
) {
    private var indent = 0

    operator fun Any?.unaryPlus() = apply { append(this) }
    fun write(vararg values: Any) = apply { values.forEach { +it } }

    fun startBlock() = apply {
        +" {"
        indent()
        newline()
    }

    fun endBlock() = apply {
        newline()
        unindent()
        +"} "
    }

    fun newline() = apply { lines += "\t".repeat(indent) }
    fun indent(apply: Boolean = false) = apply {
        indent++
        if (apply) append("\t")
    }
    fun unindent(apply: Boolean = true) = apply {
        indent--
        if (apply) lines.update(lines.lastIndex) { if (it.lastOrNull() == '\t') it.dropLast(1) else it }
    }

    fun dropNewlines() {
        lines.dropLastWhile {
            it.replace("\t", "").isBlank()
        }
    }

    fun braced(vararg values: Any, left: Char = '(', right: Char = ')') = apply {
        +left
        values.forEach { +it }
        +right
    }

    fun pop(amount: Int = 1) {
        lines.dropLast(amount)
    }

    private fun append(value: Any?) {
        lines.update(lines.lastIndex) { it + stringify(value) }
    }

    private fun stringify(value: Any?) = when (value) {
        is CodeWritable -> value.writeCode().toString()
        is String -> value
        else -> value.toString()
    }

    override fun toString() = if (lines.size == 1) lines.first() else lines.joinToString("\n")

}
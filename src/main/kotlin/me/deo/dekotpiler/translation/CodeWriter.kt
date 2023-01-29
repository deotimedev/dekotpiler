package me.deo.dekotpiler.translation

import me.deo.dekotpiler.util.update

inline fun codeWriter(closure: Code.() -> Unit) =
    Code().apply(closure)

class Code(
    private val lines: MutableList<String> = mutableListOf(""),
) {
    private var indent = 0

    operator fun String.unaryPlus() = apply { append(this) }
    fun write(value: String) = apply { +value }

    fun writeCode(code: Code) = apply {
        +code.toString()
    }

    fun startBlock() = apply {
        +" {"
        indent()
        newline()
    }

    fun endBlock() = apply {
        unindent()
        +"}"
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

    private fun append(value: String) {
        lines.update(lines.lastIndex) { it + value }
    }

    override fun toString() = lines.joinToString("\n")

}
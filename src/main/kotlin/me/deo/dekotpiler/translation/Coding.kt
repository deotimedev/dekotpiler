package me.deo.dekotpiler.translation

import me.deo.dekotpiler.util.update

inline fun buildCode(closure: Code.() -> Unit) =
    Code().apply(closure)

fun emptyCode() = Code()
fun codeOf(vararg values: Any?) = buildCode { values.forEach { +it } }
class Code(
    private val lines: MutableList<String> = mutableListOf(""),
) {
    private var indent = 0

    operator fun Any?.unaryPlus() = apply { append(this) }
    fun write(vararg values: Any?) = apply { values.forEach { +it } }

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

    inline fun blocked(closure: () -> Unit) {
        startBlock()
        closure()
        endBlock()
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

    inline fun braced(left: Char = '(', right: Char = ')', closure: () -> Unit) = apply {
        +left
        closure()
        +right
    }

    inline fun quoted(quote: Char = '\"', closure: () -> Unit) = apply {
        +quote
        closure()
        +quote
    }

    fun line(value: Any?) {
        newline()
        write(value)
    }

    // warning: majorly cursed alert
    // i will maybe fix sometime
    private fun append(value: Any?) {
        when (value) {
            is Codable -> append(value.code())
            else ->  {
                val str = value.toString()
                str.split("\n").fold(false) { new, line ->
                    if (new) newline()
                    lines.update(lines.lastIndex) {
                        it + line.toString()
                    }
                    true
                }

            }
        }
    }

    override fun toString() = if (lines.size == 1) lines.first() else lines.joinToString("\n")

}
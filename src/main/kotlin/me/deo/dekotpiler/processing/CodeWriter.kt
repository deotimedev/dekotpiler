package me.deo.dekotpiler.processing

import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.Statement
import me.deo.dekotpiler.util.update

inline fun Translator.Context.codeWriter(closure: Code.() -> Unit) =
    Code(this).apply(closure)


class Code(
    private val context: Translator.Context,
    private val lines: MutableList<String> = mutableListOf(""),
) {
    private var indent = 0
    private var pendingNewline = -1

    operator fun String.unaryPlus() = apply { append(this) }
    fun write(value: String) = apply { +value }

    fun writeStatement(statement: Statement) = apply {
        +with(context) { translateStatement(statement) }
    }

    fun writeExpression(expression: Expression) = apply {
        +with(context) { translateExpression(expression) }
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
            println("ittt: $it")
            it.replace("\t", "").isBlank()
        }
    }

    private fun append(value: String) {
        lines.update(lines.lastIndex) { it + value }
    }

    override fun toString() = lines.joinToString("\n")

}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.codeWriter

data class KtBlockStatement(
    val statements: List<KtStatement>
) : KtStatement {

    override fun writeCode() = codeWriter {
        write("{")
        indent()
        statements.forEach {
            newline()
            write(it)
        }
        newline()
        unindent()
        write("}")
    }

}
package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.BreakStmt
import com.github.javaparser.ast.stmt.SwitchStmt
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import org.springframework.stereotype.Component

@Component
class SwitchStatementTranslator : StatementTranslator<SwitchStmt> {
    override val type = SwitchStmt::class

    override fun Translator.Context.translate(value: SwitchStmt) = codeWriter {
        write("when (${translateExpression(value.selector)})")
        startBlock()
        value.entries.fold(false) { chain, branch ->
            if (branch.statements.isEmpty()) {
                writeExpression(branch.labels.first())
                write(", ")
                return@fold true
            }
            if (branch.labels.isEmpty()) write("default")
            else writeExpression(branch.labels.first())
            write(" -> ")
            (branch.statements.first() as BlockStmt)
                .statements.dropLastWhile { it is BreakStmt }
                .forEach { writeStatement(it) }
            newline()
            false
        }
        endBlock()
    }
}
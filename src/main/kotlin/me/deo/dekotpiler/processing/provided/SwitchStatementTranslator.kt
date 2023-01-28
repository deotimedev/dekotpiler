package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.BreakStmt
import com.github.javaparser.ast.stmt.SwitchStmt
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class SwitchStatementTranslator : StatementTranslator<SwitchStmt> {
    override val type = SwitchStmt::class

    override fun Translator.Context.translate(value: SwitchStmt) = buildString {
        // and the most cursed code award goes to...
        append("when (${translateExpression(value.selector)}) {")
        value.entries.fold(false) { chain, branch ->
            if (!chain) append("\n\t")
            if (branch.statements.isEmpty()) {
                append(translateExpression(branch.labels.first()), ", ")
                return@fold true
            }
            if (branch.labels.isEmpty()) append("default")
            else append(translateExpression(branch.labels.first()))
            append(" -> ")
            (branch.statements.first() as BlockStmt)
                .statements.dropLastWhile { it is BreakStmt }
                .forEach { append(translateStatement(it)) }
            false
        }
        append("\n}")
    }
}
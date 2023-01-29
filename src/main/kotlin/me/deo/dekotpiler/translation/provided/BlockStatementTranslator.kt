package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : StatementTranslator<Block, KtBlockStatement> {
    override val type = Block::class
    override fun Translation.translation(value: Block) =
        // todo fix comments being weird
        KtBlockStatement(value.blockStatements.filter { it.statement !is StructuredComment }.map { stmt ->
            println("type: ${stmt.statement::class.java.simpleName}")
            // todo remove this only for testing
            runCatching { translateStatement(stmt.statement) }.getOrElse {
                println("failed: $it")
                KtUnknown(stmt.statement.toString())
            }
        })
}
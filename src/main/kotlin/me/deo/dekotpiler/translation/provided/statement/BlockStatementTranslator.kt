package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.util.task
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : Translator<Block, KtBlockStatement> {
    override val type = Block::class
    override fun Translation.translation(value: Block) =
        // todo fix comments being weird
        KtBlockStatement(value.blockStatements.filter { it.statement !is StructuredComment }.map { stmt ->
            // todo remove this only for testing
            task("Translating statement ${stmt.statement::class.simpleName}") {
                runCatching { translateStatement(stmt.statement) }.getOrElse {
                    println("failed: $it")
                    it.printStackTrace()
                    KtUnknown(stmt.statement)
                }
            }
        }.toMutableList())
}
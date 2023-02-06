package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.KtBlock
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : StatementTranslator<Block, KtBlock> {
    override val type = Block::class
    override fun Translation.translation(value: Block) =
        // todo fix comments being weird
        KtBlock(value.blockStatements.filter { it.statement !is StructuredComment }.map { stmt ->
            // todo remove this only for testing
            runCatching { translateStatement(stmt.statement) }.getOrElse {
                println("failed: $it")
                it.printStackTrace()
                KtUnknown(stmt.statement.toString())
            }
        })
}
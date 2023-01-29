package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.provided.KtBlockStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : StatementTranslator<Block, KtBlockStatement> {
    override fun Translation.translation(value: Block) =
        KtBlockStatement(value.blockStatements.map { translate(it) })
}
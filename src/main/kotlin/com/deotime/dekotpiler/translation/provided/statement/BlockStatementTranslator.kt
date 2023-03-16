package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtUnknown
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : Translator<Block, KtBlockStatement> {

    context(Translation.Session)
    override fun translation(value: Block) =
        KtBlockStatement(value.blockStatements.filter { it.statement !is StructuredComment }.map { stmt ->
            // for testing
            runCatching { translateStatement(stmt.statement) }.getOrElse {
                println("failed: $it")
                it.printStackTrace()
                KtUnknown(stmt.statement)
            }
        }.toMutableList())
}
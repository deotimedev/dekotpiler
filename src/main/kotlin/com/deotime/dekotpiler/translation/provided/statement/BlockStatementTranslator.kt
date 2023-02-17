package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtUnknown
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import com.deotime.dekotpiler.util.task
import org.benf.cfr.reader.bytecode.analysis.structured.statement.Block
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BlockStatementTranslator : Translator<Block, KtBlockStatement> {
    
    override fun Translation.Session.translation(value: Block) =
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
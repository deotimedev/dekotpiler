package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtTryStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCatch
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredTry
import org.springframework.stereotype.Component

@Component
class TryStatementTranslator : Translator<StructuredTry, KtTryStatement> {

    override fun Translation.Session.translation(value: StructuredTry) = KtTryStatement(
        translateBlock(value.tryBlock),
        value.catchBlocks.map { it.statement }.filterIsInstance<StructuredCatch>().map {
            KtTryStatement.Catch(
                translateVariable(it.catching),
                translateBlock(it.catchBlock)
            )
        }.toMutableList(),
        value.finallyBlock?.let { translateBlock(it) }
    )
}
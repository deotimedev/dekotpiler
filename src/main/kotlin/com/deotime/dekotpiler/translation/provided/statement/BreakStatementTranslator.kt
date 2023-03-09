package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtBreakStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredBreak
import org.springframework.stereotype.Component

@Component
class BreakStatementTranslator : Translator<StructuredBreak, KtBreakStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredBreak) =
        KtBreakStatement(value.breakBlock.name)
}
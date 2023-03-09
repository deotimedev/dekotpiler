package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtContinueStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredContinue
import org.springframework.stereotype.Component

@Component
class ContinueStatementTranslator : Translator<StructuredContinue, KtContinueStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredContinue) =
        KtContinueStatement(null)
}
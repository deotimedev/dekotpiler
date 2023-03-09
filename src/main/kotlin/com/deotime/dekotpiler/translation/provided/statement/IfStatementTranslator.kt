package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtIfStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIf
import org.springframework.stereotype.Component

@Component
class IfStatementTranslator : Translator<StructuredIf, KtIfStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredIf) = KtIfStatement(
        translateConditional(value.conditionalExpression),
        translateBlock(value.ifTaken),
        value.elseBlock?.let { translateBlock(it) }
    )
}
package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.expressions.KtThrowExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredThrow
import org.springframework.stereotype.Component

@Component
class ThrowStatementTranslator : Translator<StructuredThrow, KtThrowExpression> {

    context (Translation.Session)
    override fun translation(value: StructuredThrow) =
        KtThrowExpression(translateExpression(value.value))
}
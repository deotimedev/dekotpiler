package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredExpressionStatement
import org.springframework.stereotype.Component

@Component
class ExpressionStatementTranslator : Translator<StructuredExpressionStatement, KtExpression> {

    // does this need a seperate statement?... maybe
    context (Translation.Session)
    override fun translation(value: StructuredExpressionStatement): KtExpression =
        translateExpression(value.expression)
}
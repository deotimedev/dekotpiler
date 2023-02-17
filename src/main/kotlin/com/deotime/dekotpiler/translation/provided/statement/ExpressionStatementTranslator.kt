package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredExpressionStatement
import org.springframework.stereotype.Component

@Component
class ExpressionStatementTranslator : Translator<StructuredExpressionStatement, KtExpression> {
    
    // does this need a seperate statement?... maybe
    override fun Translation.Session.translation(value: StructuredExpressionStatement): KtExpression =
        translateExpression(value.expression)
}
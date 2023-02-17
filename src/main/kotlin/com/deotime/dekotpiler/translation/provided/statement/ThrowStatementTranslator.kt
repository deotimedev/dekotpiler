package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.expressions.KtThrowExpression
import com.deotime.dekotpiler.model.statements.KtBreakStatement
import com.deotime.dekotpiler.model.statements.KtCommentStatement
import com.deotime.dekotpiler.model.statements.KtContinueStatement
import com.deotime.dekotpiler.model.statements.KtReturnStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredBreak
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredContinue
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredReturn
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredThrow
import org.springframework.stereotype.Component

@Component
class ThrowStatementTranslator : Translator<StructuredThrow, KtThrowExpression> {
    
    override fun Translation.Session.translation(value: StructuredThrow) =
        KtThrowExpression(translateExpression(value.value))
}
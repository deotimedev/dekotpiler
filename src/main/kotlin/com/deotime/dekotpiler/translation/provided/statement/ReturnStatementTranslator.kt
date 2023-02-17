package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtReturnStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredReturn
import org.springframework.stereotype.Component

@Component
class ReturnStatementTranslator : Translator<StructuredReturn, KtReturnStatement> {

    override fun Translation.Session.translation(value: StructuredReturn) =
        KtReturnStatement(translateExpression(value.value), null)
}
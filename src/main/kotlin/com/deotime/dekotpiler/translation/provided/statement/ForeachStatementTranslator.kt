package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtForStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIter
import org.springframework.stereotype.Component

@Component
class ForeachStatementTranslator : Translator<StructuredIter, KtForStatement> {

    override fun Translation.Session.translation(value: StructuredIter) = KtForStatement(
        translateVariable(value.iterator),
        translateExpression(value.list),
        translateBlock(value.body)
    )
}
package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtForStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIter
import org.koin.core.annotation.Single

@Single
class ForeachStatementTranslator : Translator<StructuredIter, KtForStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredIter) = KtForStatement(
        translateVariable(value.iterator),
        translateExpression(value.list),
        translateBlock(value.body)
    )
}
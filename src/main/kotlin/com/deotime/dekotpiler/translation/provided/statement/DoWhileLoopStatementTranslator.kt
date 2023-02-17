package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import com.deotime.dekotpiler.model.statements.KtDoWhileStatement
import com.deotime.dekotpiler.model.statements.KtWhileStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredDo
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class DoWhileLoopStatementTranslator : Translator<StructuredDo, KtDoWhileStatement> {
    
    override fun Translation.Session.translation(value: StructuredDo) = KtDoWhileStatement(
        translateConditional(value.condition),
        translateBlock(value.body)
    )
}
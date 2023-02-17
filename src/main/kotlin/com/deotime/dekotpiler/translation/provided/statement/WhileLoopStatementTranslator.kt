package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import com.deotime.dekotpiler.model.statements.KtWhileStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class WhileLoopStatementTranslator : Translator<StructuredWhile, KtWhileStatement> {

    override fun Translation.Session.translation(value: StructuredWhile) = KtWhileStatement(
        translateConditional(value.condition),
        translateStatement(value.body).asBlock()
    )
}
package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.statements.KtDoWhileStatement
import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
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
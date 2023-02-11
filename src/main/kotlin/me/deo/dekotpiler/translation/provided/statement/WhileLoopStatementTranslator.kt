package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class WhileLoopStatementTranslator : Translator<StructuredWhile, KtWhileStatement> {
    override val type = StructuredWhile::class
    override fun Translation.Session.translation(value: StructuredWhile) = KtWhileStatement(
        translateConditional(value.condition),
        translateStatement(value.body).asBlock()
    )
}
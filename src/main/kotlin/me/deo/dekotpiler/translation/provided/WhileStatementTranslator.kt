package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class WhileStatementTranslator : Translator<StructuredWhile, KtWhileStatement> {
    override val type = StructuredWhile::class
    override fun Translation.translation(value: StructuredWhile) = KtWhileStatement(
        translateConditional(value.condition),
        translateStatement(value.body)
    )
}
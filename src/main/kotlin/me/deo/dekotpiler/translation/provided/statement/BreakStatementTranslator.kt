package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtBreakStatement
import me.deo.dekotpiler.model.statements.KtCommentStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredBreak
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class BreakStatementTranslator : Translator<StructuredBreak, KtBreakStatement> {
    override val type = StructuredBreak::class
    override fun Translation.translation(value: StructuredBreak) =
        KtBreakStatement(value.breakBlock.name)
}
package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtBreakStatement
import me.deo.dekotpiler.model.statements.KtCommentStatement
import me.deo.dekotpiler.model.statements.KtContinueStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredBreak
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredContinue
import org.springframework.stereotype.Component

@Component
class ContinueStatementTranslator : Translator<StructuredContinue, KtContinueStatement> {
    override val type = StructuredContinue::class
    override fun Translation.translation(value: StructuredContinue) =
        KtContinueStatement(null)
}
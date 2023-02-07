package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.statements.KtIfStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIf
import org.springframework.stereotype.Component

@Component
class IfStatementTranslator : Translator<StructuredIf, KtIfStatement> {
    override val type = StructuredIf::class
    override fun Translation.translation(value: StructuredIf) = KtIfStatement(
        translateConditional(value.conditionalExpression),
        translateStatement(value.ifTaken),
        value.elseBlock?.let { translateStatement(it) }
    )
}
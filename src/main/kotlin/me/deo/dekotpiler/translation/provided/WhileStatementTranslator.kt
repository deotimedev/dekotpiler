package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.statements.KtIfStatement
import me.deo.dekotpiler.model.statements.KtTryStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCatch
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIf
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredTry
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class WhileStatementTranslator : StatementTranslator<StructuredWhile, KtWhileStatement> {
    override val type = StructuredWhile::class
    override fun Translation.translation(value: StructuredWhile) = KtWhileStatement(
        translateConditional(value.condition),
        translateStatement(value.body)
    )
}
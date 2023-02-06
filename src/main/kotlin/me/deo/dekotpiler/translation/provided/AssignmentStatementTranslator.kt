package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.springframework.stereotype.Component

@Component
class AssignmentStatementTranslator : StatementTranslator<StructuredAssignment, KtVariableAssignmentStatement> {
    override val type = StructuredAssignment::class
    override fun Translation.translation(value: StructuredAssignment) = KtVariableAssignmentStatement(
        value.isCreator(value.lvalue),
        translateVariable(value.lvalue),
        translateExpression(value.rvalue)
    )
}
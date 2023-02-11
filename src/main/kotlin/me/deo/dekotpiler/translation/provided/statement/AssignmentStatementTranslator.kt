package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.springframework.stereotype.Component

@Component
class AssignmentStatementTranslator : Translator<StructuredAssignment, KtVariableAssignmentStatement> {
    override val type = StructuredAssignment::class
    override fun Translation.Session.translation(value: StructuredAssignment) = KtVariableAssignmentStatement(
        value.isCreator(value.lvalue),
        translateVariable(value.lvalue),
        translateExpression(value.rvalue)
    )
}
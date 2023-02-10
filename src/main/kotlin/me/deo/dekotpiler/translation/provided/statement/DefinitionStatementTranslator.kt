package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredDefinition
import org.springframework.stereotype.Component

@Component
class DefinitionStatementTranslator : Translator<StructuredDefinition, KtVariableAssignmentStatement> {
    override val type = StructuredDefinition::class
    override fun Translation.translation(value: StructuredDefinition) = KtVariableAssignmentStatement(
        true,
        translateVariable(value.lvalue),
        null
    )
}
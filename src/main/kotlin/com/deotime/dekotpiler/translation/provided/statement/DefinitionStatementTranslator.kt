package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtVariableAssignmentStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredDefinition
import org.koin.core.annotation.Single

@Single
class DefinitionStatementTranslator : Translator<StructuredDefinition, KtVariableAssignmentStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredDefinition) = KtVariableAssignmentStatement(
        true,
        translateVariable(value.lvalue),
        null
    )
}
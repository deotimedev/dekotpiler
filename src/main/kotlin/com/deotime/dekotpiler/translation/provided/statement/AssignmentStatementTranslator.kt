package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtVariableAssignmentStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.springframework.stereotype.Component

@Component
class AssignmentStatementTranslator : Translator<StructuredAssignment, KtVariableAssignmentStatement> {

    context (Translation.Session)
override fun translation(value: StructuredAssignment) = KtVariableAssignmentStatement(
        value.isCreator(value.lvalue),
        translateVariable(value.lvalue),
        translateExpression(value.rvalue)
    )
}
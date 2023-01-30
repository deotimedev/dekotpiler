package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.statements.KtIfStatement
import me.deo.dekotpiler.model.statements.KtTryStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredAssignment
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredCatch
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIf
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredTry
import org.springframework.stereotype.Component

@Component
class IfStatementTranslator : StatementTranslator<StructuredIf, KtIfStatement> {
    override val type = StructuredIf::class
    override fun Translation.translation(value: StructuredIf) = KtIfStatement(
        KtConditional(translateExpression(value.conditionalExpression.also { println("bool: ${it::class}") }), null), // Big TODO
        translateStatement(value.ifTaken),
        value.elseBlock?.let { translateStatement(it) }
    )
}
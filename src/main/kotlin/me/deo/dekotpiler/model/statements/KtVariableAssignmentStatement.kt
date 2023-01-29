package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.codeWriter

data class KtVariableAssignmentStatement(
    val declaring: Boolean,
    val variable: KtVariable,
    val expression: KtExpression
) : KtStatement {
    override fun writeCode() = codeWriter {
        if (declaring) write(if (variable.final) "val" else "var", " ")
        write(variable.name, " = ", expression) // TODO delegation could instead happen here
    }
}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.buildCode

// for testing but might be made config option
private const val ExplicitType = true

data class KtVariableAssignmentStatement(
    val declaring: Boolean,
    val variable: KtVariable,
    val expression: KtExpression
) : KtStatement {

    override fun code() = buildCode {
        if (declaring) write(if (variable.final) "val" else "var", " ")
        +variable.name
        if (ExplicitType) write(": ", variable.type)
        write(" = ", expression) // TODO delegation could instead happen here
    }
}
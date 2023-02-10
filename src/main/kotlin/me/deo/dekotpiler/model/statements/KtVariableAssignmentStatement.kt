package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.variable.KtLocalVariable

// for testing but might be made config option
private const val ExplicitType = true

data class KtVariableAssignmentStatement(
    val declaring: Boolean,
    val variable: KtLocalVariable,
    val expression: KtExpression
) : KtStatement {

    override fun code() = buildCode {
        println()
        if (declaring) write(if (variable.final) "val" else "var", " ")
        +variable.name
        if (declaring && ExplicitType) write(": ", variable.type.simpleName)
        write(" = ", expression) // TODO delegation could instead happen here
    }
}
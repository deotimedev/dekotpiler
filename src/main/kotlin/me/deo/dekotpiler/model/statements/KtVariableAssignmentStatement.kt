package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.model.variable.KtVariable

// for testing but might be made config option
private const val ExplicitType = true

class KtVariableAssignmentStatement(
    var declaring: Boolean,
    var variable: KtVariable,
    var expression: KtExpression?
) : KtStatement {

    override fun code() = buildCode {
        if (declaring) write(if (variable.final) "val" else "var", " ")
        +variable.name
        if (declaring && ExplicitType) write(": ", variable.type)
        expression?.let {
            write(" = ", it) // TODO delegation could instead happen here
        }
    }
}
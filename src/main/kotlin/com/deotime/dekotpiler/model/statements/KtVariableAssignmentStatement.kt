package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.vision.Vision.Companion.plus
import com.deotime.vision.blurred
import com.deotime.vision.vision

data class KtVariableAssignmentStatement(
    var declaring: Boolean,
    var variable: KtVariable,
    var expression: KtExpression?,
) : KtStatement {

    override val sight = blurred(::expression) + vision(::variable)
    override fun code() = buildCode {
        if (declaring) write(if (variable.final) "val" else "var", " ")
        +variable.name
        // TODO: Config option on explicit variable declaration types
        if (declaring) write(": ", variable.type)
        expression?.let {
            write(" = ", it) // TODO delegation could instead happen here
        }
    }
}
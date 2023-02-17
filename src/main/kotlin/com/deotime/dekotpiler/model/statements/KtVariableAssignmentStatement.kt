package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.dekotpiler.util.views

// for testing but might be made config option
private const val ExplicitType = true

class KtVariableAssignmentStatement(
    var declaring: Boolean,
    var variable: KtVariable,
    var expression: KtExpression?
) : KtStatement {

    override val expressionView: KtExpressionView = views(::variable, ::expression)
    override fun code() = buildCode {
        if (declaring) write(if (variable.final) "val" else "var", " ")
        +variable.name
        if (declaring && ExplicitType) write(": ", variable.type)
        expression?.let {
            write(" = ", it) // TODO delegation could instead happen here
        }
    }
}
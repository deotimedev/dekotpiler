package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.statements.KtOptionalLabel.Companion.writeLabel
import com.deotime.dekotpiler.util.vision

data class KtReturnStatement(
    var expression: KtExpression?,
    override var label: String? = null
) : KtJumpingStatement {
    override val expressionView: KtExpressionView = vision(::expression)
    override fun code() = buildCode {
        +"return"
        writeLabel()
        expression?.let { write(" ", it) }
    }
}
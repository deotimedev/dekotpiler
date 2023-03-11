package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.statements.KtOptionalLabel.Companion.writeLabel
import com.deotime.vision.blurred
import com.deotime.vision.vision

data class KtReturnStatement(
    var expression: KtExpression?,
    override var label: String? = null
) : KtJumpingStatement {
    override val sight = blurred(::expression)
    override fun code() = buildCode {
        +"return"
        writeLabel()
        expression?.let { write(" ", it) }
    }
}
package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.views

data class KtReturnStatement(
    var expression: KtExpression?,
    override var label: String? = null
) : KtLabelled {
    override val expressionView: KtExpressionView = views(::expression)
    override fun code() = buildCode {
        +"return"
        writeLabel()
        expression?.let { write(" ", it) }
    }
}
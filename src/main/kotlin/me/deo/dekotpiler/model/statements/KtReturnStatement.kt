package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

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
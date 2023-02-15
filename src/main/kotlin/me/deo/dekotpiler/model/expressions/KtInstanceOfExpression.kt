package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtInstanceOfExpression(
    var expression: KtExpression,
    var typeCheck: KtType
) : KtExpression {
    override val expressionView: KtExpressionView = views(::expression)
    override val type = KtType.Boolean
    override fun code() = buildCode {
        write(expression, " is ", typeCheck)
    }
}
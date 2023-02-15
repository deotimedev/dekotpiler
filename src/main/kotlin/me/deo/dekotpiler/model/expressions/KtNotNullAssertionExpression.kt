package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtNotNullAssertionExpression(
    var expression: KtExpression,
) : KtExpression {
    override val expressionView: KtExpressionView = views(::expression)
    override val type get() = expression.type.nullable(false)
    override fun code() = buildCode {
        write(expression, "!!")
    }
}
package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtCastExpression(
    var expression: KtExpression,
    var cast: KtType
) : KtExpression {
    override val expressionView: KtExpressionView = views(::expression)
    override val type get() = cast
    override fun code() = buildCode {
        braced { write(expression, " as ", cast) }
    }
}
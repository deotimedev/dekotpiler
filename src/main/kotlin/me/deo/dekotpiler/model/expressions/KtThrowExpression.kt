package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.util.views

data class KtThrowExpression(
    var expression: KtExpression,
) : KtExpression {
    override val expressionView: KtExpressionView = views(::expression)
    override val type = KtNothingType
    override fun code() = buildCode {
        write("throw ", expression)
    }
}
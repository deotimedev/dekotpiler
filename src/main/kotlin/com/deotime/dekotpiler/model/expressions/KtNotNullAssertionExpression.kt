package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.views

data class KtNotNullAssertionExpression(
    var expression: KtExpression,
) : KtExpression {
    override val expressionView: KtExpressionView = views(::expression)
    override val type get() = expression.type.nullable(false)
    override fun code() = buildCode {
        write(expression, "!!")
    }
}
package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.views

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
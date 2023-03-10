package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtNothingType
import com.deotime.dekotpiler.util.vision

data class KtThrowExpression(
    var expression: KtExpression,
) : KtExpression {
    override val expressionView: KtExpressionView = vision(::expression)
    override val type = KtNothingType
    override fun code() = buildCode {
        write("throw ", expression)
    }
}
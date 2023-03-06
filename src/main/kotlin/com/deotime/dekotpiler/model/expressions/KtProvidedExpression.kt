package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression

data class KtProvidedExpression(
    var expression: String
) : KtExpression {
    override fun code() = codeOf(expression)
}
package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode

data class KtInstanceOfExpression(
    var expression: KtExpression,
    var typeCheck: KtType
) : KtExpression {
    override val type = KtType.Boolean
    override fun code() = buildCode {
        write(expression, " is ", typeCheck)
    }
}
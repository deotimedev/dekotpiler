package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode

data class KtNotNullAssertionExpression(
    var expression: KtExpression,
) : KtExpression {
    override val type get() = expression.type.nullable(false)
    override fun code() = buildCode {
        write(expression, "!!")
    }
}
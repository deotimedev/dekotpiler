package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtType

data class KtThrowExpression(
    var expression: KtExpression,
) : KtExpression {
    override val type = KtType.Nothing
    override fun code() = buildCode {
        write("throw ", expression)
    }
}
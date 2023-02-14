package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType

data class KtThrowExpression(
    var expression: KtExpression,
) : KtExpression {
    override val type = KtNothingType
    override fun code() = buildCode {
        write("throw ", expression)
    }
}
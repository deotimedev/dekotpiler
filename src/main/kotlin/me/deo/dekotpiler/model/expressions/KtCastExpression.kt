package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.buildCode

data class KtCastExpression(
    var expression: KtExpression,
    var cast: KtType
) : KtExpression {
    override val type get() = cast
    override fun code() = buildCode {
        write(expression, " as ", cast)
    }
}
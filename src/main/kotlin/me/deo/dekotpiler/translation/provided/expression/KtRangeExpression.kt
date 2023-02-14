package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType

data class KtRangeExpression(
    var from: KtExpression,
    var to: KtExpression
) : KtExpression {
    override val type: KtType
        get() = from.type // TODO common types

    override fun code() = codeOf(from, "..", to)
}
package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType

data class KtRangeExpression(
    var from: KtExpression,
    var to: KtExpression,
) : KtExpression {
    override val type: KtType
        get() = from.type // TODO common types

    override fun code() = codeOf(from, "..", to)
}
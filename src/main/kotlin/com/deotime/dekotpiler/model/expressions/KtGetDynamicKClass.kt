package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

data class KtGetDynamicKClass(
    var reference: KtExpression,
) : KtExpression {
    override val sight = vision(::reference)
    override val type get() = KtType.KClass
    override fun code() = buildCode {
        write(reference, "::class")
    }
}
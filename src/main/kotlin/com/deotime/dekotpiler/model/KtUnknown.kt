package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.type.KtNothingType

// This is only used for testing and will be removed before release
internal data class KtUnknown(
    val value: Any?,
) : KtStatement, KtExpression {
    init {
        println("Unknown instance made of ${value?.javaClass?.simpleName}")
    }

    override val type = KtNothingType
    override fun code() = codeOf(value)
}
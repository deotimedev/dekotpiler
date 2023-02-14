package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType

// This is only used for testing and will be removed before release
internal data class KtUnknown(
    val value: Any?
) : KtStatement, KtExpression {
    init {
        println("Unknown instance made of ${value?.javaClass?.simpleName}")
    }
    override val type = KtNothingType
    override fun code() = codeOf(value)
}
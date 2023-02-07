package me.deo.dekotpiler.model

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.codeOf
import me.deo.dekotpiler.util.CFRStatement

// This is only used for testing and will be removed before release
internal data class KtUnknown(
    val value: Any?
) : KtStatement, KtExpression {
    override val type = KtType.Nothing
    override fun code() = codeOf(value)
}
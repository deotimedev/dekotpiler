package me.deo.dekotpiler.model

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.codeOf
import me.deo.dekotpiler.util.CFRStatement

// This is only used for testing and will be removed before release
internal data class KtUnknown(
    val value: String
) : KtStatement, KtExpression {
    override val type = KtType.Nothing
    override fun writeCode() = codeOf(value)
}
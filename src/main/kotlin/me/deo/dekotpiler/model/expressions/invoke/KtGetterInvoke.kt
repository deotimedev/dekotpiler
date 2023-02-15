package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtGetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "get"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name

    override fun code() = codeOf(reference, ".", name)
}
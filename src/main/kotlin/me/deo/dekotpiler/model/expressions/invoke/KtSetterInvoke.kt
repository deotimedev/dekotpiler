package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtType

data class KtSetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "set"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name
    override val type: KtType
        get() = actual.type

    override fun code() = codeOf(reference, ".", name, " = ", args[0])
}
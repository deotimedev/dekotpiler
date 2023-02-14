package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.codeOf

data class KtSetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "set"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name

    override fun code() = codeOf(reference, ".", name, " = ", args[0])
}
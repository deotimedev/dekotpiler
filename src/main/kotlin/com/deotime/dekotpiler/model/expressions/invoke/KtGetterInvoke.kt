package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.codeOf

data class KtGetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "get"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name

    override fun code() = codeOf(reference, ".", name)
}
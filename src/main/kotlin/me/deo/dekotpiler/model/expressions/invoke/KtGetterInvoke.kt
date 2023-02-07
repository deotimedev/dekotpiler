package me.deo.dekotpiler.model.expressions.invoke

data class KtGetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "get"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name
}
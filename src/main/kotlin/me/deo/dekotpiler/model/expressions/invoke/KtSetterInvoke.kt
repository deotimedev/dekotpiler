package me.deo.dekotpiler.model.expressions.invoke

class KtSetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "set"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name
}
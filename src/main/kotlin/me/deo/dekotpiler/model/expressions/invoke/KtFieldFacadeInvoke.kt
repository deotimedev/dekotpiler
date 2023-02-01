package me.deo.dekotpiler.model.expressions.invoke

interface KtFieldFacadeInvoke : KtMemberInvoke {
    val prefix: String
    override val name: String
        get() = super.name.removePrefix(prefix).replaceFirstChar { it.lowercase() }
}
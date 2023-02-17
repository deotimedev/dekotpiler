package com.deotime.dekotpiler.model.type

sealed class KtNothingType(override val nullable: Boolean) : KtType {
    override val name = "Nothing"
    override val qualifiedName = "kotlin.Nothing"

    override fun nullable(nullable: Boolean) =
        if (nullable) Nullable
        else KtNothingType

    companion object : KtNothingType(false)
    object Nullable : KtNothingType(true)

}
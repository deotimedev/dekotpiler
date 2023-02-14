package me.deo.dekotpiler.model.type

object KtStarType : KtType {
    override val name = "*"
    override val nullable = false
    override fun nullable(nullable: Boolean) = this
}
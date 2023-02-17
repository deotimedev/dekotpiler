package com.deotime.dekotpiler.model.type

data class KtGenericType(
    override val name: String,
    val bounds: List<KtType>,
    override val nullable: Boolean = false
) : KtType {

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

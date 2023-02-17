package com.deotime.dekotpiler.model.type

data class KtArrayType internal constructor(
    val componentType: KtType,
    override val nullable: Boolean,
) : KtType {

    override val name = "Array<${componentType.name}>"
    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

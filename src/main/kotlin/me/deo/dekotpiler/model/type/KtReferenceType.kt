package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.coding.codeOf

data class KtReferenceType(
    override val qualifiedName: String,
    val simpleName: String = qualifiedName.split(".").last().replace("$", "."), // TODO
    override val nullable: Boolean = true,
    val generics: List<KtType> = emptyList(),
) : KtType {

    override val name = buildString {
        append(simpleName)
        if (generics.isNotEmpty())
            append("<", generics.joinToString { it.name }, ">")
        if (nullable) append("?")
    }

    override val type = this
    override fun code() = codeOf(name)

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)
    fun parameterize(vararg types: KtType) = copy(generics = types.toList())


}

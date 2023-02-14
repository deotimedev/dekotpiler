package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.codeOf
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class KtArrayType internal constructor(
    val componentType: KtType,
    override val nullable: Boolean,
) : KtType {

    override val name = "Array<${componentType.name}>"
    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.codeOf
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class KtGenericType(
    override val name: String,
    val bounds: List<KtType>,
    override val nullable: Boolean = false
) : KtType {

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

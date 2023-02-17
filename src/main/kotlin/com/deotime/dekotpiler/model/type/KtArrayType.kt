package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.coding.Codable
import com.deotime.dekotpiler.coding.codeOf
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

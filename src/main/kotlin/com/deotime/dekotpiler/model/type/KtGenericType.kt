package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.coding.Codable
import com.deotime.dekotpiler.coding.codeOf
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

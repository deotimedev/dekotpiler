package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.conig.Config

data class KtGenericType(
    override val name: String,
    val bounds: List<KtType>,
    override val nullable: Boolean = Config.typesDefaultNullable
) : KtType {

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

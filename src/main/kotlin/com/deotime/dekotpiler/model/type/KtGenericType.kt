package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.config.impl.TestingConfig

data class KtGenericType(
    override val name: String,
    val bounds: List<KtType>,
    override val nullable: Boolean = TestingConfig.typesDefaultNullable,
) : KtType {

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

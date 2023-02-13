package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtType.Companion.isPrimitive

data class KtGetDynamicKClass(
    var reference: KtExpression
) : KtExpression {
    override val type get() = KtType.KClass
    override fun code() = buildCode {
        write(reference, "::class")
    }
}
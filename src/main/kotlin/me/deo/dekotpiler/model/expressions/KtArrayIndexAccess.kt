package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.type.KtArrayType

data class KtArrayIndexAccess(
    var array: KtExpression,
    var index: KtExpression
) : KtExpression {
    override val type get() = (array.type as KtArrayType).componentType.nullable(true)
    override fun code() = buildCode {
        write(array)
        braced('[', ']') { +index }
    }
}
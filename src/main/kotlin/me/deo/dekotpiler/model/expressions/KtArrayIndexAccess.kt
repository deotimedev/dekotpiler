package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtType.Companion.isPrimitive

data class KtArrayIndexAccess(
    var array: KtExpression,
    var index: KtExpression
) : KtExpression {
    override val type get() = array.type.generics.first().nullable() // TODO type polymorphism
    override fun code() = buildCode {
        write(array)
        braced('[', ']') { +index }
    }
}
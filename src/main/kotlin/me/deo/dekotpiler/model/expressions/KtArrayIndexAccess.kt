package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.type.KtArrayType
import me.deo.dekotpiler.util.views

data class KtArrayIndexAccess(
    var array: KtExpression,
    var index: KtExpression
) : KtExpression {
    override val expressionView: KtExpressionView = views(::array, ::index)
    override val type get() = (array.type as KtArrayType).componentType.nullable(true)
    override fun code() = buildCode {
        write(array)
        braced('[', ']') { +index }
    }
}
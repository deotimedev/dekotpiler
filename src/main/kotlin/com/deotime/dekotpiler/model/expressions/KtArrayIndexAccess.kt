package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtArrayType
import com.deotime.vision.vision

data class KtArrayIndexAccess(
    var array: KtExpression,
    var index: KtExpression,
) : KtExpression {
    override val sight = vision(::array, ::index)
    override val type get() = (array.type as KtArrayType).componentType.nullable(true)
    override fun code() = buildCode {
        write(array)
        braced('[', ']') { +index }
    }
}
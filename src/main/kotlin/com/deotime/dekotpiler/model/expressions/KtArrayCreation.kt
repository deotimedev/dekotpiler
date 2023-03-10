package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtType.Companion.isPrimitive
import com.deotime.dekotpiler.util.vision

data class KtArrayCreation(
    var componentType: KtType,
    var size: KtExpression,
    var initializers: MutableList<KtExpression>? = null
) : KtExpression {
    override val expressionView: KtExpressionView = vision(::size, ::initializers)
    override val type get() = KtType.array(componentType)
    override fun code() = buildCode {
        if (componentType.isPrimitive) {
            write(componentType.name.lowercase(), "ArrayOf")
            writeInvoker(initializers.orEmpty())
        } else {
            initializers?.let {
                +"arrayOf"
                if (it.isEmpty()) writeGeneric(componentType) else writeInvoker(it)
            } ?: run {
                +"arrayOfNulls"
                writeGeneric(componentType)
                charArrayOf()
                braced { +size }
            }
        }
    }
}
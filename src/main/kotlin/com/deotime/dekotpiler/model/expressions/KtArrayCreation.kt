package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtType.Companion.isPrimitive
import com.deotime.vision.vision
import com.deotime.vision.visions

data class KtArrayCreation(
    var componentType: KtType,
    var size: KtExpression,
    val initializers: MutableList<KtExpression> = mutableListOf(),
    val nullDefault: Boolean = false,
) : KtExpression {
    override val sight = vision(::size) + visions(::initializers)
    override val type get() = KtType.array(componentType)
    override fun code() = buildCode {
        if (componentType.isPrimitive) {
            write(componentType.name.lowercase(), "ArrayOf")
            writeInvoker(initializers)
        } else {
            if (!nullDefault) {
                +"arrayOf"
                if (initializers.isEmpty()) writeGeneric(componentType) else writeInvoker(initializers)
            } else {
                +"arrayOfNulls"
                writeGeneric(componentType)
                charArrayOf()
                braced { +size }
            }
        }
    }
}
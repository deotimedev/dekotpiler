package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.type.KtType.Companion.isPrimitive

data class KtArrayCreation(
    var componentType: KtType,
    var size: KtExpression,
    var initializers: MutableList<KtExpression>? = null
) : KtExpression {
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
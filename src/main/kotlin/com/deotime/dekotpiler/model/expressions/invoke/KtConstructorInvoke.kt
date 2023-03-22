package com.deotime.dekotpiler.model.expressions.invoke


import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.vision.vision

data class KtConstructorInvoke(
    override var function: KtFunctionDescriptor,
    override val args: MutableList<KtExpression>,
) : KtInvoke {
    override val sight = vision(::args)
    override fun code() = buildCode {
        write(function.enclosing)
        writeInvoker(args)
    }
}
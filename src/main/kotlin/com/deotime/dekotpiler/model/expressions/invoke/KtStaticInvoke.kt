package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.vision.vision

// JVM Static invoke
data class KtStaticInvoke(
    override var function: KtFunctionDescriptor,
    override val args: MutableList<KtExpression>,
) : KtInvoke {

    override val sight = vision(::args)
    override fun code() = buildCode {
        if ((function as? KtFunction)?.kind != KtFunction.Kind.TopLevel)
            write(function.enclosing.name, ".")
        +function.name
        writeInvoker(args)
    }

}

package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.util.views
import kotlin.jvm.internal.Intrinsics

// Not to be confused with a companion object invoke
data class KtStaticInvoke(
    override var function: KtFunctionDescriptor,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val expressionView: KtExpressionView = views(::args)
    override fun code() = buildCode {
        if ((function as? KtFunction)?.kind != KtFunction.Kind.TopLevel)
            write(function.enclosing.name, ".")
        +function.name
        writeArgs()
    }

}

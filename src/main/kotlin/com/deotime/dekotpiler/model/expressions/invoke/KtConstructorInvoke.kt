package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.util.views

data class KtConstructorInvoke(
    override var function: KtFunction,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val expressionView: KtExpressionView = views(::args)
    override fun code() = buildCode {
        write(function.enclosing)
        writeInvoker(args)
    }
}
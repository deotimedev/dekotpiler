package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.vision

data class KtIfElseExpression(
    val condition: KtConditional,
    val then: KtExpression,
    val orElse: KtExpression
) : KtExpression {
    override val expressionView: KtExpressionView = vision(::condition, ::then, ::orElse)

    // TODO need a way to find a common type
    override val type get() = then.type
    override fun code() = buildCode {
        write("if ")
        braced { +condition }
        write(" ", then, " else ", orElse)
    }
}
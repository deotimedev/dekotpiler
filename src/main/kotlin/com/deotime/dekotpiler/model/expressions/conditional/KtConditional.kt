package com.deotime.dekotpiler.model.expressions.conditional

import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType

interface KtConditional : KtExpression {
    val inverse: Boolean
    override val type get() = KtType.Boolean

    companion object {
        context (CodeBuilder)
        fun KtConditional.writeNegated() {
            if (inverse) +"!"
        }
    }
}


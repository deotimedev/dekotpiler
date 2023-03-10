package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.vision

data class KtGetDynamicKClass(
    var reference: KtExpression
) : KtExpression {
    override val expressionView: KtExpressionView = vision(::reference)
    override val type get() = KtType.KClass
    override fun code() = buildCode {
        write(reference, "::class")
    }
}
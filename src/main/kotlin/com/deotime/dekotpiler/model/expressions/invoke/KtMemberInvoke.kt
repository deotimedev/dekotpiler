package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView

interface KtMemberInvoke : KtInvoke {
    val reference: KtExpression
    val extension: Boolean
}
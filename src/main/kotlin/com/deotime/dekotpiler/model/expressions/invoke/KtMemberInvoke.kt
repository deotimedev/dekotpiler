package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.model.KtExpression

interface KtMemberInvoke : KtInvoke {
    val reference: KtExpression
    val extension: Boolean
}
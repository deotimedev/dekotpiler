package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.model.KtExpression

interface KtMemberInvoke : KtInvoke {
    val reference: KtExpression
}
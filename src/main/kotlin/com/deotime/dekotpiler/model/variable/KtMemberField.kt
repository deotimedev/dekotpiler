package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.vision

data class KtMemberField(
    var member: KtExpression,
    override var name: String,
    override var final: Boolean,
    override val type: KtType,
    override var synthetic: Boolean
) : KtField {
    override val expressionView: KtExpressionView = vision(::member)
    override fun code() = buildCode {
        write(member, member.nullCheckedChain(), name)
    }
}
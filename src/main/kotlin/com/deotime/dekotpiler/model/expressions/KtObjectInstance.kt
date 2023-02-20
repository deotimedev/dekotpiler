package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtTyped
import com.deotime.dekotpiler.model.variable.KtStaticField

data class KtObjectInstance(
    var field: KtStaticField
) : KtExpression {
    override val type get() = field.type

    override fun code() = codeOf(field.declaring.name)
}
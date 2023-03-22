package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.variable.KtStaticField
import com.deotime.dekotpiler.model.variable.KtVariable

data class KtObjectInstance(
    var field: KtStaticField,
) : KtExpression, KtVariable {
    override val name = "INSTANCE"
    override var final = true
    override val synthetic = true
    override val type get() = field.type

    override fun code() = codeOf(field.declaring.name)
}
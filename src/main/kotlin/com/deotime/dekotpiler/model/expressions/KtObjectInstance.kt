package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.variable.KtStaticField
import com.deotime.dekotpiler.model.variable.KtVariable

// todo this shouldnt really be a variable and is only used as a workaround for an issue that needs to be fixed
data class KtObjectInstance(
    var field: KtStaticField,
) : KtExpression, KtVariable {
    override val name = "INSTANCE"
    override var final = true
    override val synthetic = false
    override val type get() = field.type

    override fun code() = codeOf(field.declaring.name)
}
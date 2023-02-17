package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.codeOf

interface KtFieldFacadeInvoke : KtMemberInvoke {
    val actual: KtMemberInvoke
    val prefix: String
    override val name: String
        get() = super.name.removePrefix(prefix).replaceFirstChar { it.lowercase() }


}
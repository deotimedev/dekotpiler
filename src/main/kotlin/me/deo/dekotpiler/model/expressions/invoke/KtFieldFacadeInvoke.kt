package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.codeOf

interface KtFieldFacadeInvoke : KtMemberInvoke {
    val actual: KtMemberInvoke
    val prefix: String
    override val name: String
        get() = super.name.removePrefix(prefix).replaceFirstChar { it.lowercase() }


}
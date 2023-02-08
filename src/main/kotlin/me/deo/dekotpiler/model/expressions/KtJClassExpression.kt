package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.buildCode

data class KtJClassExpression(
    var clazz: KtLiteral.Class
) : KtExpression {
    override val type get() = KtType.JClass
    override fun code() = buildCode {
        write(clazz, ".java")
    }
}
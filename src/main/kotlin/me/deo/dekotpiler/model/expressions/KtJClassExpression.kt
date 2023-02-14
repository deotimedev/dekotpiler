package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.util.Either
import me.deo.dekotpiler.util.unwrap

data class KtJClassExpression(
    var clazz: Either<KtLiteral.Class, KtGetDynamicKClass>
) : KtExpression {
    override val type get() = KtType.JClass
    override fun code() = buildCode {
        write(clazz.unwrap<KtExpression>(), ".java")
    }
}
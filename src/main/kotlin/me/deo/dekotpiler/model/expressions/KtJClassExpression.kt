package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.Either
import me.deo.dekotpiler.util.left
import me.deo.dekotpiler.util.unwrap
import me.deo.dekotpiler.util.viewEither
import me.deo.dekotpiler.util.views

data class KtJClassExpression(
    var clazz: Either<KtLiteral.Class, KtGetDynamicKClass>
) : KtExpression {
    override val expressionView: KtExpressionView =
        listOf(left(viewEither(::clazz)))
    override val type get() = KtType.JClass
    override fun code() = buildCode {
        write(clazz.unwrap<KtExpression>(), ".java")
    }
}
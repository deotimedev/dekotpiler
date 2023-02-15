package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtGetDynamicKClass(
    var reference: KtExpression
) : KtExpression {
    override val expressionView: KtExpressionView = views(::reference)
    override val type get() = KtType.KClass
    override fun code() = buildCode {
        write(reference, "::class")
    }
}
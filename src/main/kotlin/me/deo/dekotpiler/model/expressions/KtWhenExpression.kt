package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.codeWriter

data class KtWhenExpression(
    var value: KtExpression,
    val branches: MutableList<Branch>,
) : KtExpression {
    // TODO need a way to determine common types
    override val type: KtType get() = branches.first().expression.type
    data class Branch(
        var targets: MutableList<KtExpression>, // if empty then this branch is an `else`
        var expression: KtExpression
    )
}
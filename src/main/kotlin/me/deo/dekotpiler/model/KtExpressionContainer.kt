package me.deo.dekotpiler.model

import me.deo.dekotpiler.util.Lens

interface KtExpressionContainer {
    val expressions: List<Lens<*, out KtExpression>> get() = emptyList()
}

package me.deo.dekotpiler.model

import me.deo.dekotpiler.util.ViewableList

typealias KtExpressionView = ViewableList<Any, KtExpression?>

interface KtExpressionViewable {
    val expressionView: KtExpressionView get() = emptyList()
}

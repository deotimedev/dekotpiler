package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.util.ViewableList

typealias KtExpressionView = ViewableList<Any, KtExpression?>

interface KtExpressionViewable {
    val expressionView: KtExpressionView get() = emptyList()
}

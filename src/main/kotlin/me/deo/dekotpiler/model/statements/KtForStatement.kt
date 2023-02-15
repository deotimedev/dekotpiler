package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.views

data class KtForStatement(
    var item: KtLocalVariable,
    var iterable: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override val expressionView: KtExpressionView = views(::item, ::iterable)

    override fun code() = buildCode {
        +"for "
        braced { write(item, " in ", iterable) }
        blocked { +body }
    }



}
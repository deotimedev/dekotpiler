package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.util.views

data class KtWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override val expressionView: KtExpressionView = views(::condition)
    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +body }
    }



}
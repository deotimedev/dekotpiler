package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.views

data class KtDoWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {
    override val expressionView: KtExpressionView = views(::condition)

    override fun code() = buildCode {
        +"do"
        blocked { +body }
        +"while "
        braced { +condition }
    }


}
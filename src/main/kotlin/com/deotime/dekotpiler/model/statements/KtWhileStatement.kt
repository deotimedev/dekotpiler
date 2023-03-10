package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.util.vision

data class KtWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override val expressionView: KtExpressionView = vision(::condition)
    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +body }
    }


}
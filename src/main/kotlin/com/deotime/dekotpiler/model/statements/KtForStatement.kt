package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.util.views

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
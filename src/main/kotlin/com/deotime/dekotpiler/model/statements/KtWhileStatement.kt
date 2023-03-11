package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.vision.vision

data class KtWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override val sight = vision(::condition)
    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +body }
    }


}
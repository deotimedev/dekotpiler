package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode

data class KtWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +body }
    }



}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.variable.KtLocalVariable

data class KtForStatement(
    var item: KtLocalVariable,
    var container: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override fun code() = buildCode {
        +"for "
        braced { write(item, " in ", container) }
        blocked { +body }
    }



}
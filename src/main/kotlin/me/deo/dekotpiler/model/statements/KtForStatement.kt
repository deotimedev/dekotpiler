package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.lenses

data class KtForStatement(
    var item: KtLocalVariable,
    var iterable: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {

    override val expressions = lenses(::item, ::iterable)

    override fun code() = buildCode {
        +"for "
        braced { write(item, " in ", iterable) }
        blocked { +body }
    }



}
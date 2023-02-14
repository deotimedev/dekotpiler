package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.util.Lens
import me.deo.dekotpiler.util.lenses

data class KtDoWhileStatement(
    var condition: KtExpression,
    override var body: KtBlockStatement
) : KtSingleBodyStatement {
    override val expressions = lenses(::condition)

    override fun code() = buildCode {
        +"do"
        blocked { +body }
        +"while "
        braced { +condition }
    }



}
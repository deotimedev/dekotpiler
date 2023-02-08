package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.util.singleOf

data class KtWhileStatement(
    var condition: KtExpression,
    override var body: KtStatement
) : KtBodyStatement {

    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +body }
    }



}
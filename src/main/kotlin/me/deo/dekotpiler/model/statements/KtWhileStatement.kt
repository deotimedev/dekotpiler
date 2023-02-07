package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.buildCode

data class KtWhileStatement(
    var condition: KtExpression,
    var action: KtStatement
) : KtStatement {

    override fun code() = buildCode {
        write("while ")
        braced { +condition }
        blocked { +action }
    }



}
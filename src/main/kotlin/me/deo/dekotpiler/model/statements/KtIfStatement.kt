package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode

data class KtIfStatement(
    var condition: KtConditional,
    var then: KtStatement,
    var orElse: KtStatement?
) : KtStatement {
    override val type: KtType
        get() = super.type // TODO HOW TO FIND COMMON SUPER TYPE!?
    override fun code() = buildCode {
        write("if ")
        braced { +condition }
        blocked { write(then) }
        orElse?.let {
            write(" else ", it)
        }
    }

}
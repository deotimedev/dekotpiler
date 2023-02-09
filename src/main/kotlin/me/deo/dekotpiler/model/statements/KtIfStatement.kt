package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock

data class KtIfStatement(
    var condition: KtConditional,
    var then: KtStatement,
    var orElse: KtStatement?
) : KtMultiBodyStatement {
    override val bodies: List<KtBlockStatement>
        get() = listOfNotNull(then.asBlock(), orElse?.asBlock())
    override fun code() = buildCode {
        write("if ")
        braced { +condition }
        blocked { write(then) }
        orElse?.let {
            write(" else ", it)
        }
    }

}
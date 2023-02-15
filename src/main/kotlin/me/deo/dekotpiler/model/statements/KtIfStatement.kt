package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.util.views

data class KtIfStatement(
    var condition: KtConditional,
    var then: KtBlockStatement,
    var orElse: KtBlockStatement?
) : KtMultiBodyStatement {
    override val expressionView: KtExpressionView = views(::condition)
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
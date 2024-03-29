package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional

import com.deotime.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import com.deotime.vision.vision

data class KtIfStatement(
    var condition: KtConditional,
    var then: KtBlockStatement,
    var orElse: KtBlockStatement?,
) : KtMultiBodyStatement {
    override val sight = vision(::condition)

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
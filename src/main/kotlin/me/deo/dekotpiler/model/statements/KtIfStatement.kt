package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtLocal
import me.deo.dekotpiler.translation.codeWriter

data class KtIfStatement(
    var condition: KtConditional,
    var then: KtStatement,
    var orElse: KtStatement?
) : KtStatement {

    override fun writeCode() = codeWriter {
        write("if ")
        braced(condition).write(" ")
        write(then)
        orElse?.let {
            write(" else ", it)
        }
    }

}
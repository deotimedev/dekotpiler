package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

data class KtCastExpression(
    var expression: KtExpression,
    var cast: JavaTypeInstance
) : KtExpression {
    override fun writeCode() = codeWriter {
        write(expression, " as ", cast)
    }
}
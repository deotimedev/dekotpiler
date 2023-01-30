package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

data class KtCastExpression(
    var expression: KtExpression,
    var cast: KtType
) : KtExpression {
    override val type get() = cast
    override fun writeCode() = codeWriter {
        write(expression, " as ", cast)
    }
}
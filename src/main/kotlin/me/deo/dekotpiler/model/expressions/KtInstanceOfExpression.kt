package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

data class KtInstanceOfExpression(
    var expression: KtExpression,
    var typeCheck: JavaTypeInstance
) : KtExpression {

}
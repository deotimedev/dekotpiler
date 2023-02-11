package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

interface KtInvoke : KtExpression, KtStatement {
    val method: MethodPrototype // This can be replaced once a wrapper for method is made
    val args: MutableList<KtExpression>
    val name: String get() = method.name
    val extension: Boolean
}
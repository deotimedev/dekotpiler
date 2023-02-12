package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.function.KtFunction
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

interface KtInvoke : KtExpression, KtStatement {
    val method: KtFunction
    val args: MutableList<KtExpression>
    val name: String get() = method.name
    val extension: Boolean
    override val type
        get() = method.returns
}
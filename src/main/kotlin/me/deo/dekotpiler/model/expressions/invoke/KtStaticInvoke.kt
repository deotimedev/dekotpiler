package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

// Not to be confused with a companion object invoke
// Can this also be used as a constructor?
data class KtStaticInvoke(
    var enclosingType: KtType,
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
}
package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

class KtSetterInvoke(
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
    override var reference: KtExpression,
    override var extension: Boolean
) : KtFieldFacadeInvoke {
    override val prefix = "set"
}
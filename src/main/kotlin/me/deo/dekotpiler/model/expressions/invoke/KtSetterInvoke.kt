package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

class KtSetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "set"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name
}
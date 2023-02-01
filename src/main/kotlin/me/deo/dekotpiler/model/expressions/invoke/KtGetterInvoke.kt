package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

data class KtGetterInvoke(
    override val actual: KtMemberInvoke
) : KtFieldFacadeInvoke, KtMemberInvoke by actual {
    override val prefix = "get"
    override val name: String
        get() = super<KtFieldFacadeInvoke>.name
}
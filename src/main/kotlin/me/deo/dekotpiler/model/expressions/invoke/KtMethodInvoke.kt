package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

data class KtMethodInvoke(
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
    override var reference: KtExpression,
    override var extension: Boolean
) : KtMemberInvoke
package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

class KtEqualityCheck(
    override val method: MethodPrototype,
    override var reference: KtExpression,
    var comparing: KtExpression,
    var hard: Boolean = false
) : KtMemberInvoke {
    override val args: MutableList<KtExpression> get() = mutableListOf(comparing)
    override val extension = false
    override val type = KtType.Boolean
    override val name: String get() = if (hard) "===" else "=="

    override fun code() = buildCode {
        write(reference, " $name ", comparing)
    }
}
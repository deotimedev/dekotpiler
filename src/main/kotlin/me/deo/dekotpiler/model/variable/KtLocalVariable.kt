package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable

data class KtLocalVariable(
    override val delegate: LocalVariable,
    override var name: String,
    override var final: Boolean,
    override var type: KtType,
    var value: KtExpression? = null,
) : KtVariable {

    var uses = -1

    val inlinable get() = uses <= 1
    val inlined get() = inlinable && value != null

    override fun code() = codeOf(if (inlined) value else name)
}

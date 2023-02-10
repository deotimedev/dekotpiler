package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

class KtComparisonInvoke(
    override var reference: KtExpression,
    var comparing: KtExpression,
    var mode: Type
) : KtMemberInvoke, KtConditional(
    reference
) {
    override val args: MutableList<KtExpression> get() = mutableListOf(comparing)
    override val extension = false
    override val type = KtType.Boolean
    override val method: MethodPrototype
        get() = TODO()

    override fun code() = buildCode {
        write(reference, " ${mode.symbol} ", comparing)
    }

    enum class Type(val symbol: String) {
        Equality("=="),
        NotEquality("!="),
        ReferenceEquality("==="),
        NotReferenceEquality("!=="),
        LessThan("<"),
        GreaterThan(">"),
        LessThanOrEqual("<="),
        GreaterThanOrEqual(">=")
    }
}
package com.deotime.dekotpiler.model.expressions.conditional

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.vision.vision

data class KtComparison(
    var reference: KtExpression,
    var comparing: KtExpression,
    var mode: Type,
) : KtConditional {

    override var inverse = false

    override val sight = vision(::reference, ::comparing)

    override fun code() = buildCode {
        write(
            reference, " ${
                mode.let { if (inverse) it.inverse else it }.symbol
            } ", comparing
        )
    }

    enum class Type(val symbol: String, private val _inverse: () -> Type) {
        ReferenceEquality("===", { NotReferenceEquality }),
        NotReferenceEquality("!==", { ReferenceEquality }),
        LessThan("<", { GreaterThanOrEqual }),
        GreaterThan(">", { LessThanOrEqual }),
        LessThanOrEqual("<=", { GreaterThan }),
        GreaterThanOrEqual(">=", { LessThan });

        val inverse get() = _inverse()
    }
}
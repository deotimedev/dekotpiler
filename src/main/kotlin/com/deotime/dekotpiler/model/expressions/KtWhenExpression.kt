package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.Eyes
import com.deotime.vision.Vision.Companion.plus
import com.deotime.vision.eyesight
import com.deotime.vision.vision

data class KtWhenExpression(
    var value: KtExpression,
    val branches: MutableList<Branch>,
) : KtExpression {
    override val sight = vision(::value) + eyesight(::branches)

    // TODO: Determine shared types
    override val type: KtType get() = branches.first().expression.type

    override fun code() = buildCode {
        +"when "
        braced { +value }
        blocked {
            branches.forEach { branch ->
                if (branch.targets.isEmpty()) +"else"
                else writeJoined(branch.targets)
                write(" -> ", branch.expression)
            }
        }
    }

    data class Branch(
        val targets: MutableList<KtExpression>, // if empty then this branch is an `else`
        var expression: KtExpression,
    ) : Eyes<KtExpression> {
        override val sight = vision(::expression) + vision(::targets)
    }
}
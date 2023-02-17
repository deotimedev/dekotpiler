package com.deotime.dekotpiler.model.expressions.arithmetic

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.dekotpiler.util.views
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp

data class KtArithmeticPostMutationExpression(
    var mutating: KtVariable,
    var operation: ArithOp // i will maybe make a kotlin wrapper when not lazy
) : KtExpression {
    override val expressionView: KtExpressionView = views(::mutating)
    override val type get() = mutating.type

    init {
        mutating.final = false
    }

    override fun code() = buildCode {
        write(mutating, if (operation == ArithOp.PLUS) "++" else "--")
    }
}
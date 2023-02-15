package me.deo.dekotpiler.model.expressions.arithmetic

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.util.views
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
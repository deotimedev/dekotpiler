package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.model.expressions.KtInstanceOfExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BoolOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.InstanceOfExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class BooleanOperationTranslator : ExpressionTranslator<BooleanOperation, KtConditional> {
    override val type = BooleanOperation::class
    override fun Translation.translation(value: BooleanOperation) =
        translateConditional(value.lhs).apply {
            joined = Joined(translateOp(value.op), translateConditional(value.rhs))
        }

    private fun translateOp(op: BoolOp) = when (op) {
        BoolOp.OR -> KtConditional.Operation.Or
        BoolOp.AND -> KtConditional.Operation.And
    }
}
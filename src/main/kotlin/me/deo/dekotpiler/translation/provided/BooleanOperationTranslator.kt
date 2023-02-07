package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BoolOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanOperation
import org.springframework.stereotype.Component

@Component
class BooleanOperationTranslator : Translator<BooleanOperation, KtConditional> {
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
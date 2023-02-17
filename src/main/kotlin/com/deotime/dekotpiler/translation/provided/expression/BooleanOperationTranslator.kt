package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BoolOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanOperation
import org.springframework.stereotype.Component

@Component
class BooleanOperationTranslator : Translator<BooleanOperation, KtConditional> {
    
    override fun Translation.Session.translation(value: BooleanOperation) =
        translateConditional(value.lhs).apply {
            joined = Joined(translateOp(value.op), translateConditional(value.rhs))
        }

    private fun translateOp(op: BoolOp) = when (op) {
        BoolOp.OR -> KtConditional.Operation.Or
        BoolOp.AND -> KtConditional.Operation.And
    }
}
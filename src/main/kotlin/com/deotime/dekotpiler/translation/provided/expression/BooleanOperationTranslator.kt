package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.model.expressions.conditional.KtJoinedConditional
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BoolOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanOperation
import org.koin.core.annotation.Single

@Single
class BooleanOperationTranslator : Translator<BooleanOperation, KtConditional> {

    context (Translation.Session)
    override fun translation(value: BooleanOperation) =
        KtJoinedConditional(
            translateConditional(value.lhs),
            translateConditional(value.rhs),
            translateOp(value.op),
        )

    private fun translateOp(op: BoolOp) = when (op) {
        BoolOp.OR -> KtJoinedConditional.Operation.Or
        BoolOp.AND -> KtJoinedConditional.Operation.And
    }
}
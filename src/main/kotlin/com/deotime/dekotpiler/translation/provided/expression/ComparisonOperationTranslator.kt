package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.conditional.KtComparison
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CompOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ComparisonOperation
import org.koin.core.annotation.Single

@Single
class ComparisonOperationTranslator : Translator<ComparisonOperation, KtComparison> {

    context (Translation.Session)
    override fun translation(value: ComparisonOperation) =
        KtComparison(
            translateExpression(value.lhs),
            translateExpression(value.rhs),
            translateOp(value.op)
        )


    private fun translateOp(op: CompOp) = when (op) {
        CompOp.EQ -> KtComparison.Type.ReferenceEquality
        CompOp.NE -> KtComparison.Type.NotReferenceEquality
        CompOp.GT -> KtComparison.Type.GreaterThan
        CompOp.LT -> KtComparison.Type.LessThan
        CompOp.GTE -> KtComparison.Type.GreaterThanOrEqual
        CompOp.LTE -> KtComparison.Type.LessThanOrEqual
    }
}
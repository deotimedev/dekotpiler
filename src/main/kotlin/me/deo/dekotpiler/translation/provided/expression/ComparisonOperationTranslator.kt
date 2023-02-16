package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.expressions.invoke.KtComparison
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CompOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ComparisonOperation
import org.springframework.stereotype.Component

@Component
class ComparisonOperationTranslator : Translator<ComparisonOperation, KtComparison> {
    
    override fun Translation.Session.translation(value: ComparisonOperation) =
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
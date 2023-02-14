package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.expressions.invoke.KtComparisonInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BoolOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CompOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ComparisonOperation
import org.springframework.stereotype.Component

@Component
class ComparisonOperationTranslator : Translator<ComparisonOperation, KtComparisonInvoke> {
    
    override fun Translation.Session.translation(value: ComparisonOperation) =
        KtComparisonInvoke(
            translateExpression(value.lhs),
            translateExpression(value.rhs),
            translateOp(value.op)
        )


    private fun translateOp(op: CompOp) = when (op) {
        CompOp.EQ -> KtComparisonInvoke.Type.ReferenceEquality
        CompOp.NE -> KtComparisonInvoke.Type.NotReferenceEquality
        CompOp.GT -> KtComparisonInvoke.Type.GreaterThan
        CompOp.LT -> KtComparisonInvoke.Type.LessThan
        CompOp.GTE -> KtComparisonInvoke.Type.GreaterThanOrEqual
        CompOp.LTE -> KtComparisonInvoke.Type.LessThanOrEqual
    }
}
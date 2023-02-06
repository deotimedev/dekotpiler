package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtStringExpression
import me.deo.dekotpiler.translation.ExpressionTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.springframework.stereotype.Component
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Component
class StringConcatTranslator : ExpressionTranslator<ArithmeticOperation, KtStringExpression> {
    override val type = ArithmeticOperation::class
    override fun ArithmeticOperation.proc() =
        op == ArithOp.PLUS && (isStringLiteral(lhs) || isStringLiteral(rhs))

    override fun Translation.translation(value: ArithmeticOperation) =
        KtStringExpression(listOf(value.lhs, value.rhs).map {
            if (isStringLiteral(it))
                KtStringExpression.Element.Literal(
                    it.value.value.toString()
                        // drop quotes (could probably do this better)
                        .drop(1)
                        .dropLast(1)
                )
            else KtStringExpression.Element.Template(translateExpression(it))
        }.toMutableList())

    @OptIn(ExperimentalContracts::class)
    private fun isStringLiteral(ex: Expression): Boolean {
        contract { returns(true) implies (ex is Literal) }
        return ex is Literal && ex.value.type == TypedLiteral.LiteralType.String
    }
}
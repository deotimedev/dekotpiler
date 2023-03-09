package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.model.expressions.KtStringExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.springframework.stereotype.Component
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Component
class StringConcatTranslator : Translator<ArithmeticOperation, KtStringExpression> {

    override fun ArithmeticOperation.match() =
        op == ArithOp.PLUS && (isStringLiteral(lhs) || isStringLiteral(rhs))

    context (Translation.Session)
    override fun translation(value: ArithmeticOperation) =
        KtStringExpression(listOf(value.lhs, value.rhs).map {
            if (isStringLiteral(it))
                KtLiteral.String(
                    it.value.value.toString()
                        // drop quotes (could probably do this better)
                        .drop(1)
                        .dropLast(1)
                )
            else translateExpression(it)
        }.toMutableList())

    @OptIn(ExperimentalContracts::class)
    private fun isStringLiteral(ex: Expression): Boolean {
        contract { returns(true) implies (ex is Literal) }
        return ex is Literal && ex.value.type == TypedLiteral.LiteralType.String
    }
}
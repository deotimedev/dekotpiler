package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.model.expressions.KtStringExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import com.deotime.dekotpiler.util.isStringy
import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.koin.core.annotation.Single
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@Single
class StringConcatTranslator : Translator<ArithmeticOperation, KtStringExpression> {

    override fun ArithmeticOperation.match() = isStringy()

    context (Translation.Session)
    override fun translation(value: ArithmeticOperation) =
        KtStringExpression(listOf(value.lhs, value.rhs).map {
            if (isLiteralStringLiteral(it))
                KtLiteral.String(
                    it.value.value.toString()
                        // drop quotes
                        .drop(1)
                        .dropLast(1)
                )
            else translateExpression(it)
        }.toMutableList())

    @OptIn(ExperimentalContracts::class)
    private fun isLiteralStringLiteral(ex: Expression): Boolean {
        contract { returns(true) implies (ex is Literal) }
        return ex is Literal && ex.value.type == TypedLiteral.LiteralType.String
    }
}
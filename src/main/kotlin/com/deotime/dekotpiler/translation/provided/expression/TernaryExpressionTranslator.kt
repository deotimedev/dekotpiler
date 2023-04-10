package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtIfElseExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.koin.core.annotation.Single

@Single
class TernaryExpressionTranslator : Translator<TernaryExpression, KtIfElseExpression> {

    context (Translation.Session)
    override fun translation(value: TernaryExpression) =
        KtIfElseExpression(
            translateConditional(value.condition),
            translateExpression(value.lhs),
            translateExpression(value.rhs)
        )
}
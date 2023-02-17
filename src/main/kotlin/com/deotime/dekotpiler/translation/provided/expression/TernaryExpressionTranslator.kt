package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtIfElseExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.TernaryExpression
import org.springframework.stereotype.Component

@Component
class TernaryExpressionTranslator : Translator<TernaryExpression, KtIfElseExpression> {

    override fun Translation.Session.translation(value: TernaryExpression) =
        KtIfElseExpression(
            translateConditional(value.condition),
            translateExpression(value.lhs),
            translateExpression(value.rhs)
        )
}
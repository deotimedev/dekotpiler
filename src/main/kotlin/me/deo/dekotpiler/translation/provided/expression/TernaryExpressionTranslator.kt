package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.expressions.KtIfElseExpression
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
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
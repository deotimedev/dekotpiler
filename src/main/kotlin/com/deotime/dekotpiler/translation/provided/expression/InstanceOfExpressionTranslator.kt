package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtIsExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.InstanceOfExpression
import org.springframework.stereotype.Component

@Component
class InstanceOfExpressionTranslator : Translator<InstanceOfExpression, KtIsExpression> {

    context (Translation.Session)
    override fun translation(value: InstanceOfExpression) =
        KtIsExpression(
            translateExpression(value.lhs),
            translateType(value.typeInstance)
        )
}
package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtCastExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.springframework.stereotype.Component

@Component
class CastExpressionTranslator : Translator<CastExpression, KtCastExpression> {

    override fun Translation.Session.translation(value: CastExpression) =
        KtCastExpression(
            translateExpression(value.child),
            translateType(value.inferredJavaType.javaTypeInstance)
        )
}
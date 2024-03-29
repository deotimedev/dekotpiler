package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtCastExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.koin.core.annotation.Single

@Single
class CastExpressionTranslator : Translator<CastExpression, KtCastExpression> {

    context (Translation.Session)
    override fun translation(value: CastExpression) =
        KtCastExpression(
            translateExpression(value.child),
            translateType(value.inferredJavaType.javaTypeInstance)
        )
}
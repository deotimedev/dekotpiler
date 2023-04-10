package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.model.expressions.conditional.KtConditionalLike
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.koin.core.annotation.Single

@Single
class BooleanExpressionTranslator : Translator<BooleanExpression, KtConditional> {

    context (Translation.Session)
    override fun translation(value: BooleanExpression) =
        KtConditionalLike(translateExpression(value.inner))
}
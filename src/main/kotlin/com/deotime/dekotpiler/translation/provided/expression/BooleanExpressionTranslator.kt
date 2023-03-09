package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.springframework.stereotype.Component

@Component
class BooleanExpressionTranslator : Translator<BooleanExpression, KtConditional> {

    context (Translation.Session)
override fun translation(value: BooleanExpression) =
        KtConditional(translateExpression(value.inner))
}
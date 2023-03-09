package com.deotime.dekotpiler.translation.provided.variable

import com.deotime.dekotpiler.model.variable.KtVariable
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.LValueExpression
import org.springframework.stereotype.Component

@Component
class LValueTranslator : Translator<LValueExpression, KtVariable> {

    context (Translation.Session)
    override fun translation(value: LValueExpression) =
        translateVariable<KtVariable>(value.lValue)
}
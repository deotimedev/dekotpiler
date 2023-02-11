package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.LValueExpression
import org.springframework.stereotype.Component

@Component
class LValueTranslator : Translator<LValueExpression, KtVariable> {
    override val type = LValueExpression::class
    override fun Translation.Session.translation(value: LValueExpression) =
        translateVariable<KtVariable>(value.lValue)
}
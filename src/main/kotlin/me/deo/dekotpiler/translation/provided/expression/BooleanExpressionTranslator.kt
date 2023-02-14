package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.springframework.stereotype.Component

@Component
class BooleanExpressionTranslator : Translator<BooleanExpression, KtConditional> {
    
    override fun Translation.Session.translation(value: BooleanExpression) =
        KtConditional(translateExpression(value.inner))
}
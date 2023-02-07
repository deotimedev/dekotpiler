package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.springframework.stereotype.Component

@Component
class BooleanExpressionTranslator : Translator<BooleanExpression, KtConditional> {
    override val type = BooleanExpression::class
    override fun Translation.translation(value: BooleanExpression) =
        KtConditional(translateExpression(value.inner))
}
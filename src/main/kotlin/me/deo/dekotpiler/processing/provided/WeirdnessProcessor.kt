package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.invoke.KtComparison
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class WeirdnessProcessor :
    PreProcessor<KtComparison> {

    override fun KtComparison.match() =
        mode == KtComparison.Type.NotReferenceEquality
                && reference is KtLocalVariable && comparing is KtLiteral.Int
    override fun replace(value: KtComparison) =
        value.reference

}
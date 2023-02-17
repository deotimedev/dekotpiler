package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.model.expressions.invoke.KtComparison
import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.processing.PreProcessor
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
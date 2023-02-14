package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.expressions.KtGetDynamicKClass
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.invoke.KtComparisonInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.model.variable.KtVariable
import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.util.right
import org.springframework.stereotype.Component

@Component
class WeirdnessProcessor :
    PreProcessor<KtComparisonInvoke> {

    override fun KtComparisonInvoke.match() =
        mode == KtComparisonInvoke.Type.NotReferenceEquality
                && reference is KtLocalVariable && comparing is KtLiteral.Int
    override fun replace(value: KtComparisonInvoke) =
        value.reference

}
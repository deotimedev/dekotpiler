package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.translation.provided.expression.KtRangeExpression
import org.springframework.stereotype.Component

@Component
class RangeConstructionProcessor :
    PreProcessor<KtConstructorInvoke>,
    Matcher<KtConstructorInvoke> by RangeConstructionProcessor {

    override fun replace(value: KtConstructorInvoke) =
        KtRangeExpression(
            value.args[0],
            value.args[1]
        )

    companion object {
        private val Ranges = listOf(KtType.IntRange, KtType.LongRange, KtType.CharRange)
        val RangeConstructionProcessor = Matcher<KtConstructorInvoke> {
            method.enclosing in Ranges
        }
    }
}
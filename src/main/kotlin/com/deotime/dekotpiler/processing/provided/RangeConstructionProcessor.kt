package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.processing.PreProcessor
import com.deotime.dekotpiler.model.expressions.KtRangeExpression
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
            function.enclosing in Ranges
        }
    }
}
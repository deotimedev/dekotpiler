package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.expressions.invoke.KtComparisonInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.translation.provided.expression.KtRangeExpression
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

@Component
class RangeConstructionProcessor :
    PreProcessor<KtConstructorInvoke>,
    ClassMatcher<KtConstructorInvoke> by RangeConstructionProcessor {

    override fun replace(value: KtConstructorInvoke) =
        KtRangeExpression(
            value.args[0],
            value.args[1]
        )

    companion object {
        private val Ranges = listOf(KtType.IntRange, KtType.LongRange, KtType.CharRange)
        val RangeConstructionProcessor = ClassMatcher<KtConstructorInvoke>() + Matcher {
            method.enclosing in Ranges
        }
    }
}
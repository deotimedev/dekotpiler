package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.expressions.invoke.KtComparisonInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

@Component
class IntrinsicEqualityCheckProcessor :
    PreProcessor<KtStaticInvoke>,
    Matcher<KtStaticInvoke> by IntrinsicEqualityCheckMatcher {

    override fun replace(value: KtStaticInvoke) =
        KtComparisonInvoke(
            value.args[0],
            value.args[1],
            KtComparisonInvoke.Type.Equality
        )

    companion object {
        val IntrinsicEqualityCheckMatcher = KtStaticInvoke.Matcher<Intrinsics>("areEqual")
    }
}
package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.expressions.invoke.KtComparison
import me.deo.dekotpiler.model.expressions.invoke.KtMemberInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtMethodInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

@Component
class IntrinsicEqualityCheckProcessor :
    PreProcessor<KtStaticInvoke>,
    Matcher<KtStaticInvoke> by IntrinsicEqualityCheckMatcher {

    override fun replace(value: KtStaticInvoke) =
        KtMethodInvoke(
            KtFunction.Equals,
            value.args[0],
            mutableListOf(value.args[1])
        )

    companion object {
        val IntrinsicEqualityCheckMatcher = KtStaticInvoke.Matcher<Intrinsics>("areEqual")
    }
}
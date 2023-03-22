package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.model.expressions.invoke.KtMethodInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke.Companion.staticInvoke
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.processing.PreProcessor
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
        val IntrinsicEqualityCheckMatcher = Matchers.staticInvoke<Intrinsics>("areEqual")
    }
}
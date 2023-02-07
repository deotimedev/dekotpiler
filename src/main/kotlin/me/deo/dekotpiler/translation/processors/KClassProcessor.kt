package me.deo.dekotpiler.translation.processors

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.provided.StaticFunctionInvokeMatcher
import me.deo.dekotpiler.matching.provided.TypeMatcher
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.translation.PostProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor :
    PostProcessor<KtStaticInvoke>,
    Matcher<KtStaticInvoke> by TypeMatcher<KtStaticInvoke>() + StaticFunctionInvokeMatcher<Reflection>(GetOrCreateKClassName) {

    override val type = KtStaticInvoke::class

    override fun replace(value: KtStaticInvoke) =
        (value.args.first() as KtJClassExpression).clazz

    companion object {
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
    }
}
package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.provided.StaticFunctionInvokeMatcher
import me.deo.dekotpiler.matching.provided.TypeMatcher
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor :
    PreProcessor<KtStaticInvoke>,
    TypeMatcher<KtStaticInvoke> by TypeMatcher<KtStaticInvoke>() + StaticFunctionInvokeMatcher<Reflection>(
        GetOrCreateKClassName
    ) {

    override fun replace(value: KtStaticInvoke) =
        (value.args.first() as KtJClassExpression).clazz

    companion object {
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
    }
}
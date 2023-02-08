package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor :
    PreProcessor<KtStaticInvoke>,
    ClassMatcher<KtStaticInvoke> by KClassMatcher {

    override fun replace(value: KtStaticInvoke) =
        (value.args[0] as KtJClassExpression).clazz

    companion object {
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
        val KClassMatcher = KtStaticInvoke.Matcher<Reflection>(GetOrCreateKClassName)
    }
}
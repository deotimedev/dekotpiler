package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.expressions.KtGetDynamicKClass
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor :
    PreProcessor<KtStaticInvoke>,
    Matcher<KtStaticInvoke> by KClassMatcher {

    override fun replace(value: KtStaticInvoke): Any? = when (val arg = value.args[0]) {
        is KtJClassExpression -> arg.clazz
        is KtGetterInvoke -> KtGetDynamicKClass(arg.reference)
        else -> value
    }

    companion object {
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
        val KClassMatcher = KtStaticInvoke.Matcher<Reflection>(GetOrCreateKClassName)
    }
}
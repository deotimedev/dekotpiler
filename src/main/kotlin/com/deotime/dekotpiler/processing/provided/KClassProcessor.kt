package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.model.expressions.KtGetDynamicKClass
import com.deotime.dekotpiler.model.expressions.KtJClassExpression
import com.deotime.dekotpiler.model.expressions.invoke.KtGetterInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.processing.PreProcessor
import com.deotime.dekotpiler.util.unwrap
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
        val KClassMatcher = Matchers.staticFunction<Reflection>(GetOrCreateKClassName)
    }
}
package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.expressions.KtGetDynamicKClass
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor :
    PreProcessor<KtStaticInvoke>,
    ClassMatcher<KtStaticInvoke> by KClassMatcher {

    override fun replace(value: KtStaticInvoke): Any? {
        val arg = value.args[0]
        return if (arg is KtJClassExpression)
            arg.clazz
        else if (arg is KtGetterInvoke)
            KtGetDynamicKClass(arg.reference)
        else value
    }

    companion object {
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
        val KClassMatcher = KtStaticInvoke.Matcher<Reflection>(GetOrCreateKClassName)
    }
}
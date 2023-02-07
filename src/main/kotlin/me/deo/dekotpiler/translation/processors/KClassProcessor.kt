package me.deo.dekotpiler.translation.processors

import me.deo.dekotpiler.model.KtUnknown
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.translation.PostProcessor
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Reflection

@Component
class KClassProcessor : PostProcessor<KtStaticInvoke> {
    override val type = KtStaticInvoke::class

    override fun KtStaticInvoke.match() =
        enclosingType.rawName == ReflectionClassName && name == GetOrCreateKClassName
    override fun replace(value: KtStaticInvoke) =
        (value.args.first() as KtJClassExpression).clazz

    companion object {
        const val ReflectionClassName = "kotlin.jvm.internal.Reflection"
        const val GetOrCreateKClassName = "getOrCreateKotlinClass"
    }
}
package me.deo.dekotpiler.matching.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.expressions.invoke.KtInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.CodeWritable
import me.deo.dekotpiler.translation.processors.KClassProcessor
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KFunction1

class StaticFunctionInvokeMatcher(
    val className: String,
    val functionName: String
) : Matcher<KtStaticInvoke> {

    override fun KtStaticInvoke.match() =
        enclosingType.rawName == className && name == functionName

    companion object {
        inline operator fun <reified T> invoke(functionName: String) =
            StaticFunctionInvokeMatcher(T::class.qualifiedName!!, functionName)
    }
}
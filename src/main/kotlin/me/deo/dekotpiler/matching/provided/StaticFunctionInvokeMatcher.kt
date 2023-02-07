package me.deo.dekotpiler.matching.provided

import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke

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
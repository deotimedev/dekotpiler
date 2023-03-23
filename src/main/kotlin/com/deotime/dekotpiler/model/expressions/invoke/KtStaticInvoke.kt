package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.vision.vision

// JVM Static invoke
data class KtStaticInvoke(
    override var function: KtFunctionDescriptor,
    override val args: MutableList<KtExpression>,
) : KtInvoke {

    override val sight = vision(::args)
    override fun code() = buildCode {
        if ((function as? KtFunction)?.kind != KtFunction.Kind.TopLevel)
            write(function.enclosing.name, ".")
        +function.name
        writeInvoker(args)
    }

    companion object {
        inline fun <reified T> Matchers.staticInvoke(vararg names: String) =
            Matcher<KtStaticInvoke> {
                function.enclosing.qualifiedName == T::class.qualifiedName && name in names
            }
    }

}

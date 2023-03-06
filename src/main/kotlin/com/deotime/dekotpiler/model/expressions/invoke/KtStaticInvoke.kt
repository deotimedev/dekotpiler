package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.util.views

// Not to be confused with a companion object invoke
data class KtStaticInvoke(
    override var function: KtFunctionDescriptor,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val expressionView: KtExpressionView = views(::args)
    override fun code() = buildCode {
        if ((function as? KtFunction)?.kind != KtFunction.Kind.TopLevel)
            write(function.enclosing.name, ".")
        +function.name
        writeArgs()
    }

    class Matcher(
        val className: String,
        val functionNames: List<String>
    ) : com.deotime.dekotpiler.matching.Matcher<KtStaticInvoke> {

        override fun KtStaticInvoke.match() =
            function.enclosing?.qualifiedName == className && name in functionNames

        companion object {
            inline operator fun <reified T> invoke(vararg functionNames: String) =
                Matcher(T::class.qualifiedName!!, functionNames.toList())
        }
    }
}
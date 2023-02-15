package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtExpressionView
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.util.views

// Not to be confused with a companion object invoke
data class KtStaticInvoke(
    override var method: KtFunction,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val expressionView: KtExpressionView = views(::args)
    override val extension = false
    override fun code() = buildCode {
        if (method.kind != KtFunction.Kind.TopLevel)
            write(method.enclosing?.name, ".")
        +method.name
        writeArgs()
    }

    class Matcher(
        val className: String,
        val functionNames: List<String>
    ) : me.deo.dekotpiler.matching.Matcher<KtStaticInvoke> {

        override fun KtStaticInvoke.match() =
            method.enclosing?.qualifiedName == className && name in functionNames

        companion object {
            inline operator fun <reified T> invoke(vararg functionNames: String) =
                Matcher(T::class.qualifiedName!!, functionNames.toList())
        }
    }
}
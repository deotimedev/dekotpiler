package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.function.KtFunction
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

// Not to be confused with a companion object invoke
data class KtStaticInvoke(
    override var method: KtFunction,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(method.enclosing?.simpleName, ".", method.name)
        braced { +args.joinToString { it.code().toString() } }
    }

    class Matcher(
        val className: String,
        val functionNames: List<String>
    ) : ClassMatcher<KtStaticInvoke> {

        override val clazz = KtStaticInvoke::class
        override fun KtStaticInvoke.match() =
            method.enclosing?.typeName == className && name in functionNames

        companion object {
            inline operator fun <reified T> invoke(vararg functionNames: String) =
                Matcher(T::class.qualifiedName!!, functionNames.toList())
        }
    }
}
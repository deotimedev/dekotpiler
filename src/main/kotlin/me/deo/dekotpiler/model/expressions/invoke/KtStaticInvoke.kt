package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.matching.ClassMatcher
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

// Not to be confused with a companion object invoke
data class KtStaticInvoke(
    var enclosingType: KtType,
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(enclosingType.simpleName, ".", method.name)
        braced { +args.joinToString { it.code().toString() } }
    }

    class Matcher(
        val className: String,
        val functionName: String
    ) : ClassMatcher<KtStaticInvoke> {

        override val clazz = KtStaticInvoke::class
        override fun KtStaticInvoke.match() =
            enclosingType.typeName == className && name == functionName

        companion object {
            inline operator fun <reified T> invoke(functionName: String) =
                Matcher(T::class.qualifiedName!!, functionName)
        }
    }
}
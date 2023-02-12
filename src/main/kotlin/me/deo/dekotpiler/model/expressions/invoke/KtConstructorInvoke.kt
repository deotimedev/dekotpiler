package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.function.KtFunction
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

data class KtConstructorInvoke(
    override var method: KtFunction,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(method.enclosing)
        braced { +args.joinToString { it.code().toString() } }
    }
}
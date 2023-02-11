package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.matching.ClassMatcher
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

data class KtConstructorInvoke(
    var constructing: KtType,
    override var method: MethodPrototype,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(constructing)
        braced { +args.joinToString { it.code().toString() } }
    }
}
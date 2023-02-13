package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.structure.KtFunction

data class KtArrayConstructorInvoke(
    override var method: KtFunction,
    override var args: MutableList<KtExpression>,
) : KtInvoke {
    override val extension = false
    override fun code() = buildCode {
        write(method.enclosing)
        braced { +args.joinToString { it.code().toString() } }
    }
}
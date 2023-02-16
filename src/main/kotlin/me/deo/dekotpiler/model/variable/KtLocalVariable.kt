package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.util.MutableQueue
import me.deo.dekotpiler.util.mutableQueueOf
import me.deo.dekotpiler.util.poll

data class KtLocalVariable(
    override var name: String,
    override var final: Boolean,
    override var type: KtType,
    override var synthetic: Boolean,
) : KtVariable {

    // todo theoretically none of this needs to exist
    // once expresion tree walking is implemented
    var uses = -1
    var inlineValue: KtExpression? = null
    val inlinable get() = uses <= 1
    val inlined get() = inlinable && inlineValue != null

    override fun code() = codeOf(if (inlined) inlineValue.also {
        println("inlined ${it?.code().toString()}")
    } else name)
}

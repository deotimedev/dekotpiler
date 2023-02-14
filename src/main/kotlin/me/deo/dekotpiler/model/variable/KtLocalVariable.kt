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

    var uses = -1

    val inlineValues: MutableQueue<KtExpression> = mutableQueueOf()
    val inlinable get() = uses <= 1
    val inlined get() = inlinable && inlineValues.isNotEmpty()

    override fun code() = codeOf(if (inlined) inlineValues.poll().also {
        println("inlined ${it?.code().toString()}")
    } else name)
}

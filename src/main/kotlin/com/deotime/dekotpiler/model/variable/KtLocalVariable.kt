package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.MutableQueue
import com.deotime.dekotpiler.util.mutableQueueOf
import com.deotime.dekotpiler.util.poll

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

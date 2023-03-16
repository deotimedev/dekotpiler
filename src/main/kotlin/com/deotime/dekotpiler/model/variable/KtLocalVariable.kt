package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType

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


    val thisRef = name == "\$this"

    override fun code() =
        codeOf(if (inlined) inlineValue.also {
            println("inlined ${it?.code().toString()}")
        } else if (thisRef) "this" else name) // todo this should encapsulate all synthetic references
}

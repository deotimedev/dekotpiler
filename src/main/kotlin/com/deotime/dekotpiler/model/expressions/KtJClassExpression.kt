package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.vision.vision

sealed interface KtJClassExpression : KtExpression {
    val clazz: KtExpression
    override val type get() = KtType.JClass
    override fun code() = codeOf(clazz, ".java")

    data class Literal(override var clazz: KtLiteral.Class) : KtJClassExpression {
        override val sight = vision(::clazz)
    }
    data class Dynamic(override var clazz: KtGetDynamicKClass) : KtJClassExpression {
        override val sight = vision(::clazz)
    }
}
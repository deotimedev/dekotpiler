package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.buildCode
import me.deo.dekotpiler.translation.codeOf
import kotlin.reflect.KClass

import kotlin.Int as KtInt
import kotlin.String as KtString

sealed class KtLiteral<T>(override val type: KtType) : KtExpression {

    abstract var value: T

    override fun code() = codeOf(value.toString())

    data class Int(override var value: KtInt) : KtLiteral<KtInt>(KtType.Int)
    data class String(override var value: KtString) : KtLiteral<KtString>(KtType.String) {
        override fun code() = buildCode {
            quoted { +value }
        }
    }
    data class Class(override var value: KtType) : KtLiteral<KtType>(KtType.KClass) {
        override fun code() = codeOf(value.nullable(false).simpleName, "::class")
    }

    // TODO rest of the literals
    data class Something(override var value: Any) : KtLiteral<Any>(KtType.Any)

    object Null : KtLiteral<Nothing?>(KtType.Nothing.nullable()) {
        override var value = null
    }
}
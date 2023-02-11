package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import kotlin.Boolean as KtBoolean
import kotlin.Double as KtDouble
import kotlin.Float as KtFloat
import kotlin.Int as KtInt
import kotlin.Long as KtLong
import kotlin.String as KtString

sealed class KtLiteral<T>(override val type: KtType, val letter: Char? = null) : KtExpression {

    abstract val value: T

    override fun code() = buildCode {
        write(value)
        letter?.let { +it }
    }

    data class Int(override var value: KtInt) : KtLiteral<KtInt>(KtType.Int)
    data class Long(override var value: KtLong) : KtLiteral<KtLong>(KtType.Long, 'L')
    data class Float(override var value: KtFloat) : KtLiteral<KtFloat>(KtType.Float, 'F')
    data class Double(override var value: KtDouble) : KtLiteral<KtDouble>(KtType.Double)
    data class String(override var value: KtString) : KtLiteral<KtString>(KtType.String) {
        override fun code() = codeOf("\"", value, "\"")
    }
    data class Class(override var value: KtType) : KtLiteral<KtType>(KtType.KClass.generics(value)) {
        override fun code() = codeOf(value.nullable(false).simpleName, "::class")
    }
    object Null : KtLiteral<Nothing?>(KtType.Nothing.nullable()) {
        override var value = null
    }

    private data class _Boolean(override val value: KtBoolean) : KtLiteral<KtBoolean>(KtType.Boolean)
    companion object {
        val True: KtLiteral<KtBoolean> = _Boolean(true)
        val False: KtLiteral<KtBoolean> = _Boolean(false)

        @Suppress("FunctionName")
        fun Boolean(value: KtBoolean) = if (value) True else False
    }
}
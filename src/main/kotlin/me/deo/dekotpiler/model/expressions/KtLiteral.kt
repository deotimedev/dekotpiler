package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType
import kotlin.Boolean as KtBoolean
import kotlin.Double as KtDouble
import kotlin.Float as KtFloat
import kotlin.Int as KtInt
import kotlin.Long as KtLong
import kotlin.String as KtString
import kotlin.Char as KtChar

sealed class KtLiteral<T>(override val type: KtType, val letter: KtChar? = null) : KtExpression {

    abstract val value: T

    override fun code() = buildCode {
        write(value)
        letter?.let { +it }
    }

    data class Int(override var value: KtInt) : KtLiteral<KtInt>(KtType.Int)
    data class Long(override var value: KtLong) : KtLiteral<KtLong>(KtType.Long, 'L')
    data class Float(override var value: KtFloat) : KtLiteral<KtFloat>(KtType.Float, 'F')
    data class Double(override var value: KtDouble) : KtLiteral<KtDouble>(KtType.Double)
    data class Char(override var value: KtChar) : KtLiteral<KtChar>(KtType.Char) {
        override fun code() = codeOf("'", value, "'")
    }
    data class String(override var value: KtString) : KtLiteral<KtString>(KtType.String) {
        override fun code() = codeOf("\"", value, "\"")
    }
    data class Class(override var value: KtType) : KtLiteral<KtType>(KtType.KClass.parameterize(value)) {
        override fun code() = codeOf(value.nullable(false).name, "::class")
    }
    object Null : KtLiteral<Nothing?>(KtNothingType.Nullable) {
        override var value = null
    }

    class Boolean internal constructor(
        val _overload: Nothing?,
        override val value: KtBoolean
        ) : KtLiteral<KtBoolean>(KtType.Boolean) {
        companion object {
            operator fun invoke(value: KtBoolean) = if (value) True else False
        }
    }

    companion object {
        val True = Boolean(null, true)
        val False = Boolean(null, false)
    }
}
package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.type.KtNothingType
import me.deo.dekotpiler.model.type.KtType
import kotlin.Boolean as KtBoolean
import kotlin.Char as KtChar
import kotlin.Double as KtDouble
import kotlin.Float as KtFloat
import kotlin.Int as KtInt
import kotlin.Long as KtLong
import kotlin.String as KtString

sealed interface KtLiteral<T> : KtExpression {

    override val type: KtType
    val letter: KtChar? get() = null
    val value: T

    override fun code() = buildCode {
        write(value)
        letter?.let { +it }
    }

    @JvmInline
    value class Int(override val value: KtInt) : KtLiteral<KtInt> {
        override val type: KtType
            get() = KtType.Int
    }
    @JvmInline
    value class Long(override val value: KtLong) : KtLiteral<KtLong> {
        override val type: KtType
            get() = KtType.Long
        override val letter: kotlin.Char?
            get() = 'L'
    }
    @JvmInline
    value class Float(override val value: KtFloat) : KtLiteral<KtFloat> {
        override val type: KtType
            get() = KtType.Float
        override val letter: kotlin.Char?
            get() = 'F'
    }
    @JvmInline
    value class Double(override val value: KtDouble) : KtLiteral<KtDouble> {
        override val type: KtType
            get() = KtType.Double
    }
    @JvmInline
    value class Char(override val value: KtChar) : KtLiteral<KtChar> {
        override val type: KtType
            get() = KtType.Char
        override fun code() = codeOf("'", value, "'")
    }

    @JvmInline
    value class String(override val value: KtString) : KtLiteral<KtString> {
        override val type: KtType
            get() = KtType.String
        override fun code() = codeOf("\"", value, "\"")
    }

    @JvmInline
    value class Class(override val value: KtType) : KtLiteral<KtType> {
        override val type: KtType
            get() = KtType.KClass.parameterize(value)
        override fun code() = codeOf(value.nullable(false).name, "::class")
    }

    data object Null : KtLiteral<Nothing?> {
        override val type = KtNothingType.Nullable
        override var value = null
    }

    @JvmInline
    value class Boolean internal constructor(
        override val value: KtBoolean
    ) : KtLiteral<KtBoolean> {
        override val type: KtType
            get() = KtType.Boolean
        companion object {
            operator fun invoke(value: KtBoolean) = if (value) True else False
        }
    }

    companion object {
        val True = Boolean(true)
        val False = Boolean(false)

        val Zero = Int(0)
        val One = Int(1)
    }
}
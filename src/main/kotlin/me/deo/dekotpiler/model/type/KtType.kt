package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.buildCode
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

interface KtType : KtTyped, Codable {
    val name: String
    val qualifiedName: String get() = name
    val nullable: Boolean
    fun nullable(nullable: Boolean = true): KtType
    override fun code() = buildCode {
        +name
        if (nullable) +"?"
    }

    override val type: KtType get() = this

    companion object {

        val Any = KtType<Any>()
        val Unit = KtType<Unit>()

        val Int = KtType<Int>()
        val Long = KtType<Long>()
        val Short = KtType<Short>()
        val Byte = KtType<Byte>()
        val Float = KtType<Float>()
        val Double = KtType<Double>()
        val Boolean = KtType<Boolean>()
        val Char = KtType<Char>()
        val String = KtType<String>()
        val KClass = KtType<KClass<*>>()
        val JClass = KtType<Class<*>>()

        // ranges
        val IntRange = KtType<IntRange>()
        val LongRange = KtType<LongRange>()
        val CharRange = KtType<CharRange>()


        val PrimitiveToArray = mapOf(
            Int to KtType<IntArray>(),
            Long to KtType<LongArray>(),
            Short to KtType<ShortArray>(),
            Byte to KtType<ByteArray>(),
            Float to KtType<FloatArray>(),
            Double to KtType<DoubleArray>(),
            Boolean to KtType<BooleanArray>(),
            Char to KtType<CharArray>()
        )

        // efficiency at its finest
        val Primitives = PrimitiveToArray.keys.toList()

        val KtType.isPrimitive get() = this in Primitives

        operator fun invoke(
            type: KClass<*>,
            nullable: Boolean,
            generics: List<KtType>
        ) =
            KtReferenceType(
                type.qualifiedName!!,
                type.simpleName!!,
                nullable,
                generics,
            )

        operator fun invoke(
            type: KType,
        ): KtReferenceType =
            invoke(
                type.classifier as KClass<*>,
                type.isMarkedNullable,
                type.arguments.map { it.type?.let(KtType::invoke) ?: KtStarType }
            )

        inline operator fun <reified T> invoke() = invoke(typeOf<T>())

        fun array(type: KtType) =
            if (type.nullable(false).isPrimitive) PrimitiveToArray[type.nullable(false)]!!
            else KtArrayType(
                type,
                nullable = false,
            )
    }
}
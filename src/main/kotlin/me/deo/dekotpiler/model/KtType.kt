package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class KtType(
    val typeName: String,
    val simpleName: String = typeName.split(".").last().replace("$", "."), // TODO
    val nullable: Boolean = true,
    val generics: List<KtType> = emptyList(),
) : KtTyped,
    Codable {

    val name = buildString {
        append(simpleName)
        if (generics.isNotEmpty())
            append("<", generics.joinToString { it.simpleName }, ">")
        if (nullable) append("?")
    }

    override val type = this
    override fun code() = codeOf(name)

    fun nullable(nullable: Boolean = true) = copy(nullable = nullable)
    fun generics(vararg types: KtType) = copy(generics = types.toList())

    companion object {

        val Star = KtType("*")

        val Any = KtType<Any>()
        val Unit = KtType<Unit>()
        val Nothing = KtType("kotlin.Nothing")

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
            KtType(
                type.qualifiedName!!,
                type.simpleName!!,
                nullable,
                generics,
            )

        operator fun invoke(
            type: KType,
        ): KtType =
            invoke(
                type.classifier as KClass<*>,
                type.isMarkedNullable,
                type.arguments.map { it.type?.let(::invoke) ?: Star }
            )

        inline operator fun <reified T> invoke() = invoke(typeOf<T>())

        fun array(type: KtType) =
            if (type.nullable(false).isPrimitive) PrimitiveToArray[type.nullable(false)]!!
            else KtType(
                "kotlin.Array",
                nullable = false,
                generics = listOf(type)
            )
    }

}

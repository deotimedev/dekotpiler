package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class KtType(
    val typeName: String,
    val simpleName: String = typeName.split(".").last(),
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

    companion object {

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
            ).let { if (type.java.isArray) array(it) else it }

        operator fun invoke(
            type: KType,
        ): KtType =
            invoke(
                type.classifier as KClass<*>,
                type.isMarkedNullable,
                type.arguments.mapNotNull { it.type?.let(::invoke) }
            )

        inline operator fun <reified T> invoke() = invoke(typeOf<T>())

        fun array(type: KtType) = KtType(
            "kotlin.Array",
            nullable = false,
            generics = listOf(type)
        )
    }

}

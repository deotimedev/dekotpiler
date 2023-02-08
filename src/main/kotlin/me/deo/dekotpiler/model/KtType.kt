package me.deo.dekotpiler.model

import me.deo.dekotpiler.matching._Matcher
import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.buildCode
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

data class KtType(
    internal val delegate: JavaTypeInstance, // TODO
    val nullable: Boolean = true
) : JavaTypeInstance by delegate,
    KtTyped,
    Codable {

    val simpleName get() =
        buildString {
            append(delegate.rawName.split(".").last())
            if (nullable) append("?")
        }

    override val type = this
    override fun code() = buildCode {
        write(delegate)
        if (nullable) +"?"
    }

    fun nullable(nullable: Boolean = true) = copy(nullable = nullable)

    companion object {

        val Any = KtType<Any>()
        val Unit = KtType<Unit>()
        val Nothing = KtType(JavaRefTypeInstance.createTypeConstant("kotlin.Nothing"), false)

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


        fun java(clazz: Class<*>): JavaRefTypeInstance = JavaRefTypeInstance.createTypeConstant(
            clazz.kotlin.qualifiedName ?: error("Glitchy class"),
            *((clazz.interfaces + clazz.superclass).filterNotNull().map(::java)).toTypedArray()
        )

        inline operator fun <reified T> invoke() =
            KtType(java(T::class.java), typeOf<T>().isMarkedNullable)
    }

    class Matcher<T>(
        val reference: (T) -> KtType,
        val type: KtType
    ) : _Matcher<T> {
        override fun T.match() = reference(this) == type
    }
}

package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.CodeWritable
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import kotlin.reflect.typeOf

data class KtType(
    private val delegate: JavaTypeInstance, // TODO
    val nullable: Boolean = true
) : JavaTypeInstance by delegate,
    KtTyped,
    CodeWritable {
    override val type = this
    override fun writeCode() = codeWriter {
        write(delegate)
        if (nullable) write("?")
    }

    fun nullable() = copy(nullable = true)

    companion object {
        val Any = KtType<Any>()
        val Unit = KtType<Unit>()
        val Boolean = KtType<Boolean>()
        val Nothing = KtType(JavaRefTypeInstance.createTypeConstant("kotlin.Nothing"), false)

        fun java(clazz: Class<*>): JavaRefTypeInstance = JavaRefTypeInstance.createTypeConstant(
            clazz.kotlin.qualifiedName ?: error("Glitchy class"),
            *((clazz.interfaces + clazz.superclass).filterNotNull().map(::java)).toTypedArray()
        )

        inline operator fun <reified T> invoke() =
            KtType(java(T::class.java), typeOf<T>().isMarkedNullable)
    }
}

package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.CodeWritable
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaRefTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType

data class KtType(
    private val delegate: JavaTypeInstance, // TODO
    val nullable: Boolean = true
) : JavaTypeInstance by delegate, CodeWritable {
    override fun writeCode() = codeWriter {
        write(delegate)
        if (nullable) write("?")
    }

    companion object {
        val Nothing = KtType(JavaRefTypeInstance.createTypeConstant("kotlin.Nothing"), false)
        val Boolean = KtType(JavaRefTypeInstance.createTypeConstant("kotlin.Boolean"), false)
    }
}

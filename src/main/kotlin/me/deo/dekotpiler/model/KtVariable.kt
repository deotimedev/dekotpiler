package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.CodeWritable
import me.deo.dekotpiler.translation.codeOf
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType

data class KtVariable(
    val name: String,
    val final: Boolean,
    val type: JavaTypeInstance
) : CodeWritable {
    override fun writeCode() = codeOf(name)
}

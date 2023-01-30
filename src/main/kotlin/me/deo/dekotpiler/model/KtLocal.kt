package me.deo.dekotpiler.model

import me.deo.dekotpiler.translation.CodeWritable
import me.deo.dekotpiler.translation.codeOf
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance

data class KtLocal(
    var name: String,
    var final: Boolean,
    override var type: KtType
) : KtExpression, CodeWritable {
    override fun writeCode() = codeOf(name)
}

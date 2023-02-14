package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.type.KtType
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.StaticVariable

data class KtStaticField(
    var declaring: KtType,
    override var name: String,
    override var final: Boolean,
    override var type: KtType,
    override var synthetic: Boolean,
    var objectReference: Boolean = false
) : KtField {
    override fun code() = buildCode {
        +declaring.name
        if (!objectReference) write(".", name)
    }
}

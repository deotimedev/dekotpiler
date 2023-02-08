package me.deo.dekotpiler.model.variable

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtType
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.StaticVariable

data class KtStaticField(
    override val delegate: StaticVariable,
    var declaring: KtType,
    override var name: String,
    override var final: Boolean,
    override var type: KtType,
    var objectReference: Boolean = false
) : KtField {
    override fun code() = buildCode {
        +declaring.simpleName
        if (!objectReference) write(".", name)
    }
}

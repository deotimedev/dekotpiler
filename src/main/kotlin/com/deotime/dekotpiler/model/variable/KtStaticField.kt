package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.type.KtType
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

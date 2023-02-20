package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.model.type.KtType

data class KtStaticField(
    var declaring: KtReferenceType,
    override var name: String,
    override var final: Boolean,
    override var type: KtType,
    override var synthetic: Boolean,
) : KtField {
    override fun code() = buildCode {
        write(+declaring.name, ".", name)
    }
}

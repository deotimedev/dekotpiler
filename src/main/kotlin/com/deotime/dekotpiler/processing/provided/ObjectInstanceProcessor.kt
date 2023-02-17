package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.model.variable.KtStaticField
import com.deotime.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor : PreProcessor<KtStaticField> {

    override fun KtStaticField.match() = name == "INSTANCE" || name == "Companion"
    override fun modify(value: KtStaticField) {
        value.objectReference = true
        value.type = value.type.nullable(false)
    }

}
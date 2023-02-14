package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor : PreProcessor<KtStaticField> {

    override fun KtStaticField.match() = name == "INSTANCE" || name == "Companion"
    override fun modify(value: KtStaticField) {
        value.objectReference = true
        value.type = value.type.nullable(false)
    }

}
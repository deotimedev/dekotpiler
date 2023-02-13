package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.matching.ValueMatcher
import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor :
    PreProcessor<KtStaticField>,
    ClassMatcher<KtStaticField> by ClassMatcher() {

    override fun modify(value: KtStaticField) {
        // FIX MATCHERS
        // ALSO CHECK EXTERNAL IF AVAILABLE
        if (value.name == "INSTANCE" || value.name == "Companion") {
            value.objectReference = true
            value.type = value.type.nullable(false)
        }

    }

    companion object {
        // TODO FIX MATCHERS PLEASE
    }
}
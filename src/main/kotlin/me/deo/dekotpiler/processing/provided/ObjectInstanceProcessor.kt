package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor :
    PreProcessor<KtStaticField>,
    ClassMatcher<KtStaticField> by ClassMatcher() {

    // need to fix match
    override fun modify(value: KtStaticField) {
        if (value.name == "INSTANCE") value.objectReference = true
    }
}
package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.matching.ValueMatcher
import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class ObjectInstanceProcessor :
    PreProcessor<KtStaticField>,
    ClassMatcher<KtStaticField> by ObjectInstanceMatcher {

    override fun modify(value: KtStaticField) {
        value.objectReference = true
    }

    companion object {
        val ObjectInstanceMatcher = ValueMatcher(KtStaticField::name, "INSTANCE")
    }
}
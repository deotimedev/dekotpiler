package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.StaticVariable
import org.springframework.stereotype.Component

@Component
class StaticFieldVariableTranslator : Translator<StaticVariable, KtStaticField> {
    

    override fun Translation.Session.translation(value: StaticVariable) = KtStaticField(
        translateType(value.owningClassType).nullable(false),
        value.rawFieldName,
        value.isFinal,
        translateType(value.inferredJavaType),
        false, // todo?
    )
}
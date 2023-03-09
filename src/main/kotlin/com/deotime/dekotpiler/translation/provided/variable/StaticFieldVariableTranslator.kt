package com.deotime.dekotpiler.translation.provided.variable

import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.model.variable.KtStaticField
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.StaticVariable
import org.springframework.stereotype.Component

@Component
class StaticFieldVariableTranslator : Translator<StaticVariable, KtStaticField> {


    context (Translation.Session)
    override fun translation(value: StaticVariable) = KtStaticField(
        translateType(value.owningClassType).nullable(false) as KtReferenceType,
        value.rawFieldName,
        value.isFinal,
        translateType(value.inferredJavaType),
        false, // todo?
    )
}
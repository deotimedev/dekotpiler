package com.deotime.dekotpiler.translation.provided.variable

import com.deotime.dekotpiler.model.variable.KtMemberField
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable
import org.springframework.stereotype.Component

@Component
class FieldVariableTranslator : Translator<FieldVariable, KtMemberField> {


    context (Translation.Session)
    override fun translation(value: FieldVariable) = KtMemberField(
        translateExpression(value.`object`),
        value.rawFieldName,
        value.isFinal,
        translateType(value.inferredJavaType),
        false // todo?
    )
}
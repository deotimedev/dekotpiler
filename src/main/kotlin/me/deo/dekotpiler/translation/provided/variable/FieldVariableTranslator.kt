package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.model.variable.KtMemberField
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class FieldVariableTranslator : Translator<FieldVariable, KtMemberField> {
    

    override fun Translation.Session.translation(value: FieldVariable) = KtMemberField(
        value,
        translateExpression(value.`object`),
        value.rawFieldName,
        value.isFinal,
        translateType(value.inferredJavaType)
    )
}
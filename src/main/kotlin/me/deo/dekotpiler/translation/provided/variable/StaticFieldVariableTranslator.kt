package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.model.variable.KtMemberField
import me.deo.dekotpiler.model.variable.KtStaticField
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.StaticVariable
import org.springframework.stereotype.Component

@Component
class StaticFieldVariableTranslator : Translator<StaticVariable, KtStaticField> {
    override val type = StaticVariable::class

    override fun Translation.Session.translation(value: StaticVariable) = KtStaticField(
        value,
        translateType(value.owningClassType).nullable(false), // TODO KtType.NonNull
        value.rawFieldName,
        value.isFinal,
        translateType(value.inferredJavaType)
    )
}
package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class LocalVariableTranslator : Translator<LocalVariable, KtLocalVariable> {
    override val type = LocalVariable::class

    // needs to be linked somehow...
    override fun Translation.translation(value: LocalVariable): KtLocalVariable = KtLocalVariable(
        value,
        value.name.stringName,
        true, // todo postprocessing for this
        translateType(value.inferredJavaType.javaTypeInstance)
    )
}
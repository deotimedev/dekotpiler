package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class LocalVariableTranslator : Translator<LocalVariable, KtVariable> {
    override val type = LocalVariable::class
    override fun Translation.translation(value: LocalVariable): KtVariable = KtVariable(
        value,
        value.name.stringName,
        true, // todo postprocessing for this
        translateType(value.inferredJavaType.javaTypeInstance)
    )
}
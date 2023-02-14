package me.deo.dekotpiler.translation.provided.variable

import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class LocalVariableTranslator : Translator<LocalVariable, KtLocalVariable> {
    

    // needs to be linked somehow...
    override fun Translation.Session.translation(value: LocalVariable): KtLocalVariable = KtLocalVariable(
        value.name.stringName,
        true,
        translateType(value.inferredJavaType.javaTypeInstance).nullable(false),
        !value.name.isGoodName && value.name.stringName.startsWith("var")
    )
}
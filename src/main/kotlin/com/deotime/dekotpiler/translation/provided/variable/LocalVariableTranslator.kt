package com.deotime.dekotpiler.translation.provided.variable

import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
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
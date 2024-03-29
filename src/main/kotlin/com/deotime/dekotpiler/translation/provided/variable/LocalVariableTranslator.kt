package com.deotime.dekotpiler.translation.provided.variable

import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.koin.core.annotation.Single

@Single
class LocalVariableTranslator : Translator<LocalVariable, KtLocalVariable> {
    context (Translation.Session)
    override fun translation(value: LocalVariable): KtLocalVariable = KtLocalVariable(
        value.name.stringName,
        true,
        translateType(value.inferredJavaType.javaTypeInstance).nullable(false),
        !value.name.isGoodName && value.name.stringName.startsWith("var")
    )
}
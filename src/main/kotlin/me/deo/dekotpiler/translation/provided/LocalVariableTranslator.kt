package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtLocal
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class LocalVariableTranslator : Translator<LocalVariable, KtLocal> {
    override val type = LocalVariable::class
    // does this need a seperate statement?... maybe
    override fun Translation.translation(value: LocalVariable): KtLocal = KtLocal(
        value.name.stringName,
        true, // todo postprocessing for this
        translateType(value.inferredJavaType.javaTypeInstance)
    )
}
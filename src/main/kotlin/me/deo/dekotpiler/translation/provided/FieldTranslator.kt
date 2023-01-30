package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.LValueExpression
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.AbstractFieldVariable
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.FieldVariable
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.LocalVariable
import org.springframework.stereotype.Component

@Component
class FieldTranslator : Translator<LValueExpression, KtVariable> {
    override val type = LValueExpression::class
    override fun Translation.translation(value: LValueExpression): KtVariable = KtVariable(
        value.lValue,
        (value.lValue as? AbstractFieldVariable)?.fieldName ?: "unknown",
        true, // todo postprocessing for this
        translateType(value.inferredJavaType.javaTypeInstance)
    )
}
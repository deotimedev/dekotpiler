package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.LValue
import org.benf.cfr.reader.bytecode.analysis.parse.expression.LValueExpression
import org.benf.cfr.reader.bytecode.analysis.parse.lvalue.AbstractFieldVariable
import org.springframework.stereotype.Component

@Component
class LValueTranslator : Translator<LValueExpression, KtVariable> {
    override val type = LValueExpression::class
    override fun Translation.translation(value: LValueExpression): KtVariable = KtVariable(
        value.lValue,
         value.lValue.toString().split(".").last(), // todo fix
        true, // todo postprocessing for this
        translateType(value.inferredJavaType.javaTypeInstance)
    )
}
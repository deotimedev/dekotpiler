package me.deo.dekotpiler.translation.provided.expression.invoke

import me.deo.dekotpiler.model.expressions.invoke.KtMethodInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.MemberFunctionInvokation
import org.springframework.stereotype.Component

@Component
class MemberFunctionInvokeTranslator : Translator<MemberFunctionInvokation, KtMethodInvoke> {
    override val type = MemberFunctionInvokation::class
    override fun Translation.Session.translation(value: MemberFunctionInvokation) = KtMethodInvoke(
        translateFunction(value.methodPrototype),
        value.args.map { translateExpression(it) }.toMutableList(),
        translateExpression(value.`object`),
        false
    )
}
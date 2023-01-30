package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtFunctionInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.MemberFunctionInvokation
import org.springframework.stereotype.Component

@Component
class MemberFunctionInvokeTranslator : Translator<MemberFunctionInvokation, KtFunctionInvoke> {
    override val type = MemberFunctionInvokation::class
    override fun Translation.translation(value: MemberFunctionInvokation) = KtFunctionInvoke(
        value.methodPrototype,
        translateExpression(value.`object`.also { println("ref: ${it::class.java}") }),
        value.args.map { translateExpression(it) }.toMutableList()
    )
}
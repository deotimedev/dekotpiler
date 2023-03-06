package com.deotime.dekotpiler.translation.provided.expression.invoke

import com.deotime.dekotpiler.model.expressions.invoke.KtMethodInvoke
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.MemberFunctionInvokation
import org.springframework.stereotype.Component

@Component
class MemberFunctionInvokeTranslator : Translator<MemberFunctionInvokation, KtMethodInvoke> {

    override fun Translation.Session.translation(value: MemberFunctionInvokation) = KtMethodInvoke(
        translateFunction(value.methodPrototype),
        translateExpression(value.`object`),
        value.args.map { translateExpression(it) }.toMutableList(),
    )
}
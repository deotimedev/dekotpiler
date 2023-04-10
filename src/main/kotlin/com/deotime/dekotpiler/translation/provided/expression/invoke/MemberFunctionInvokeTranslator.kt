package com.deotime.dekotpiler.translation.provided.expression.invoke

import com.deotime.dekotpiler.model.expressions.invoke.KtMethodInvoke
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.MemberFunctionInvokation
import org.koin.core.annotation.Single

@Single
class MemberFunctionInvokeTranslator : Translator<MemberFunctionInvokation, KtMethodInvoke> {

    context (Translation.Session)
    override fun translation(value: MemberFunctionInvokation) = KtMethodInvoke(
        translateFunction(value.methodPrototype),
        translateExpression(value.`object`),
        value.args.map { translateExpression(it) }.toMutableList(),
    )
}
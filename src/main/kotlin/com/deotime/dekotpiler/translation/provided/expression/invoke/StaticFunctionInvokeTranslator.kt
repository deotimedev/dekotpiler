package com.deotime.dekotpiler.translation.provided.expression.invoke

import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation
import org.koin.core.annotation.Single

@Single
class StaticFunctionInvokeTranslator : Translator<StaticFunctionInvokation, KtStaticInvoke> {

    context (Translation.Session)
    override fun translation(value: StaticFunctionInvokation) = KtStaticInvoke(
        translateFunction(value.methodPrototype),
        value.args.map { translateExpression(it) }.toMutableList(),
    )

}
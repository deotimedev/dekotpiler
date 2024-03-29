package com.deotime.dekotpiler.translation.provided.expression.invoke

import com.deotime.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationSimple
import org.koin.core.annotation.Single

@Single
class ConstructorFunctionInvokeTranslator : Translator<ConstructorInvokationSimple, KtConstructorInvoke> {

    context (Translation.Session)
    override fun translation(value: ConstructorInvokationSimple) = KtConstructorInvoke(
        translateFunction(value.constructorPrototype),
        value.args.map { translateExpression(it) }.toMutableList(),
    )

}
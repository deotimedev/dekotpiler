package com.deotime.dekotpiler.translation.provided.expression.invoke

import com.deotime.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationSimple
import org.springframework.stereotype.Component

@Component
class ConstructorFunctionInvokeTranslator : Translator<ConstructorInvokationSimple, KtConstructorInvoke> {

    override fun Translation.Session.translation(value: ConstructorInvokationSimple) = KtConstructorInvoke(
        translateFunction(value.constructorPrototype),
        value.args.map { translateExpression(it) }.toMutableList(),
    )

}
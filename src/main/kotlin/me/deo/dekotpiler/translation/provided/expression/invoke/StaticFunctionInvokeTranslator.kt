package me.deo.dekotpiler.translation.provided.expression.invoke

import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation
import org.springframework.stereotype.Component

@Component
class StaticFunctionInvokeTranslator : Translator<StaticFunctionInvokation, KtStaticInvoke> {
    
    override fun Translation.Session.translation(value: StaticFunctionInvokation) = KtStaticInvoke(
        translateFunction(value.methodPrototype, KtFunction.Kind.TopLevel),
        value.args.map { translateExpression(it) }.toMutableList(),
    )

}
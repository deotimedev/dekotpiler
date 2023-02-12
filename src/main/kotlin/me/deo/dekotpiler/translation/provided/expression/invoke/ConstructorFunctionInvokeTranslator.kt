package me.deo.dekotpiler.translation.provided.expression.invoke

import me.deo.dekotpiler.model.expressions.invoke.KtConstructorInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ConstructorInvokationSimple
import org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation
import org.springframework.stereotype.Component

@Component
class ConstructorFunctionInvokeTranslator : Translator<ConstructorInvokationSimple, KtConstructorInvoke> {
    override val type = ConstructorInvokationSimple::class
    override fun Translation.Session.translation(value: ConstructorInvokationSimple) = KtConstructorInvoke(
        translateFunction(value.constructorPrototype),
        value.args.map { translateExpression(it) }.toMutableList(),
    )

}
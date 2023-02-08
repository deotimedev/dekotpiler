package me.deo.dekotpiler.translation.provided.expression.invoke

import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation
import org.springframework.stereotype.Component

@Component
class StaticFunctionInvokeTranslator : Translator<StaticFunctionInvokation, KtStaticInvoke> {
    override val type = StaticFunctionInvokation::class
    override fun Translation.translation(value: StaticFunctionInvokation) = KtStaticInvoke(
        translateType(value.clazz),
        value.methodPrototype,
        value.args.map { translateExpression(it) }.toMutableList(),
    ).also { value.function.name }

}
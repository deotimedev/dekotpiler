package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.expressions.KtFunctionInvoke
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.MemberFunctionInvokation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.StaticFunctionInvokation
import org.springframework.stereotype.Component

@Component
class StaticFunctionInvokeTranslator : Translator<StaticFunctionInvokation, KtFunctionInvoke> {
    override val type = StaticFunctionInvokation::class
    override fun Translation.translation(value: StaticFunctionInvokation): KtFunctionInvoke {
        val extension = value.clazz.rawName.endsWith("Kt")
        val args = value.args.toMutableList()
        return KtFunctionInvoke(
            value.methodPrototype,
            if (extension) translateExpression(args.removeFirst()) else null,
            args.map { translateExpression(it) }.toMutableList(),
            isExtension = extension
        )
    }

}
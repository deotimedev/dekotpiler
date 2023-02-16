package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.expressions.KtArrayCreation
import me.deo.dekotpiler.model.structure.KtFunction
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.peek
import me.deo.dekotpiler.util.singleOf

interface KtInvoke : KtExpression, KtStatement {
    val function: KtFunction
    val args: MutableList<KtExpression>
    val name: String get() = function.name
    val extension: Boolean
    override val type
        get() = function.returns

    fun Code.writeArgs() {
        val mappedArgs = function.parameters.flatMapIndexed { i: Int, parameter: KtFunction.Parameter ->
            val arg = args[i]
            // todo this is so cursed please fix
            // will fix with a crawler once views are setup properly......
            if (parameter.vararg) ((arg as KtLocalVariable).inlineValues.peek() as KtArrayCreation) .initializers.orEmpty()
            else singleOf(arg)
        }
        writeInvoker(mappedArgs)
    }
}
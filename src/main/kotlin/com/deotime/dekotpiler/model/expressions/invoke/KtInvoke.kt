package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.structure.KtFunction

interface KtInvoke : KtExpression, KtStatement {
    val function: KtFunction
    val args: MutableList<KtExpression>
    val name: String get() = function.name
    override val type
        get() = function.returns

    fun Code.writeArgs() {
//        val mappedArgs = function.parameters.flatMapIndexed { i: Int, parameter: KtFunction.Parameter ->
//            val arg = args[i]
//            // todo this is so cursed please fix
//            // will fix with a crawler once views are setup properly......
//            if (parameter.vararg) ((arg as KtLocalVariable).inlineValue as KtArrayCreation).initializers.orEmpty()
//            else singleOf(arg)
//        }
        writeInvoker(args)
    }
}
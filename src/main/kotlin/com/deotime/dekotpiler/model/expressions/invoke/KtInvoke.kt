package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.backing

interface KtInvoke : KtExpression, KtStatement {
    val function: KtFunctionDescriptor
    val args: MutableList<KtExpression>
    val name: String get() = function.name
    override val type get() = function.returns


    companion object {
        val KtInvoke.explicitTypeArguments by backing { mutableListOf<KtType>() }
    }
}
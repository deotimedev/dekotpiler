package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.CodeBuilder
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.model.structure.KtFunctionDescriptor
import com.deotime.dekotpiler.model.variable.KtLocalVariable

interface KtInvoke : KtExpression, KtStatement {
    val function: KtFunctionDescriptor
    val args: MutableList<KtExpression>
    val name: String get() = function.name
    override val type
        get() = function.returns


}
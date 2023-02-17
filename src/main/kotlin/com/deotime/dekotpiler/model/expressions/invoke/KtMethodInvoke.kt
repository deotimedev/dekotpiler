package com.deotime.dekotpiler.model.expressions.invoke

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.structure.KtFunction
import com.deotime.dekotpiler.util.views

data class KtMethodInvoke(
    override var function: KtFunction,
    override var reference: KtExpression,
    override var args: MutableList<KtExpression>,
    override var extension: Boolean = false,
) : KtMemberInvoke {
    override val expressionView: KtExpressionView = views(::args, ::reference)
    override fun code() = buildCode {
        function.operator?.takeIf { !reference.type.nullable }?.let { op ->
            +op.format
                .replace("@", reference.code().toString())
                .replace("!MID!", args.dropLast(1).joinToString { it.code().toString() })
                .let { if (args.size > 1) it.replace("!LAST!", args.last().code().toString()) else it }
                .let {
                    var result = it
                    args.forEachIndexed { i, arg ->
                        result = result
                            .replace("#${i + 1}", arg.code().toString())
                    }
                    result
                }
        } ?: run {
            write(reference, reference.nullCheckedChain(), name)
            writeArgs()
        }
    }






}
package me.deo.dekotpiler.model.expressions.invoke

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.function.KtFunction
import org.benf.cfr.reader.bytecode.analysis.types.MethodPrototype

data class KtMethodInvoke(
    override var method: KtFunction,
    override var args: MutableList<KtExpression>,
    override var reference: KtExpression,
    override var extension: Boolean,
    override val name: String = method.name
) : KtMemberInvoke {
    override fun code() = buildCode {
        operator?.let { op ->
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
            braced { +args.joinToString { it.code().toString() } }
        }
    }

    val operator
        get() =
            takeUnless { reference.type.nullable }?.let { Operator.All.find { it.functionName == name } }

    companion object {
        // kotlin enums initialize very strange
        private fun arith(char: Char) = "@ $char #1"
        private fun augment(char: Char) = "@ $char= #1"
    }

    enum class Operator(val format: String) {
        UnaryPlus("+@"),
        UnaryMinus("-@"),
        Not("!@"),
        Inc("@++"),
        Dec("@--"),
        Plus(arith('+')),
        Minus(arith('-')),
        Times(arith('*')),
        Div(arith('/')),
        Rem(arith('%')),
        PlusAssign(augment('+')),
        MinusAssign(augment('-')),
        TimesAssign(augment('*')),
        DivAssign(augment('/')),
        RemAssign(augment('%')),
        RangeTo("@..#1"),
        Contains("#1 in @"),
        Get("@[!MID!]"),
        Set("@[!MID!] = !LAST!"),
        Invoke("@(!MID!, !LAST!)"), // TODO lambda
        Equals("@ == #1"),
        // TODO: delegators and compareTo
        ;

        val functionName = name.replaceFirstChar { it.lowercase() }

        companion object {
            val All by lazy { values().toSet() }
        }
    }
}
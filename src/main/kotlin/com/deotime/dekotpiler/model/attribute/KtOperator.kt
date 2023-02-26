package com.deotime.dekotpiler.model.attribute

import com.deotime.dekotpiler.model.structure.KtFunction


private fun arith(char: Char) = "@ $char #1"
private fun augment(char: Char) = "@ $char= #1"

enum class KtOperator(val format: String) {
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

    inline val functionName get() = name.replaceFirstChar { it.lowercase() }
}
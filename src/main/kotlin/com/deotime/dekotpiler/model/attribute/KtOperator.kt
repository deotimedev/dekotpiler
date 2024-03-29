package com.deotime.dekotpiler.model.attribute


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
    Invoke("@(!MID!, !LAST!)"), // TODO: This should be changed when lambdas are added
    Equals("@ == #1"),
    // TODO: getValue, setValue, provideDelegate, and compareTo still need to be implemented
    ;

    inline val functionName get() = name.replaceFirstChar { it.lowercase() }
}
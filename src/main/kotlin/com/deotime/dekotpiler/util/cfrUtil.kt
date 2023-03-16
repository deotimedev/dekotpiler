package com.deotime.dekotpiler.util

import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral

fun Expression.isStringy(): Boolean {
    if (this is ArithmeticOperation) return op == ArithOp.PLUS && (lhs.isStringy() || rhs.isStringy())
    return this is Literal && value.type == TypedLiteral.LiteralType.String
}
package com.deotime.dekotpiler.util

import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithOp
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral

fun Expression.isStringLiteralish(): Boolean {
    if (this is ArithmeticOperation) return op == ArithOp.PLUS && (lhs.isStringLiteralish() || rhs.isStringLiteralish())
    return this is Literal && value.type == TypedLiteral.LiteralType.String
}
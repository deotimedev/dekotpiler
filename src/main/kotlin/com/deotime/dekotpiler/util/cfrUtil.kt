package com.deotime.dekotpiler.util

import org.benf.cfr.reader.bytecode.analysis.parse.Expression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun Expression.isStringLiteral(): Boolean {
    contract { returns(true) implies (this@isStringLiteral is Literal) }
    return this is Literal && value.type == TypedLiteral.LiteralType.String
}
package me.deo.dekotpiler.processing

fun interface FunctionVisitor : Visitor<FunctionVisitor.Context> {
    interface Context : Visitor.Context
}
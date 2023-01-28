package me.deo.dekotpiler.processing

fun interface ExpressionVisitor : Visitor<ExpressionVisitor.Context> {
    interface Context : Visitor.Context
}
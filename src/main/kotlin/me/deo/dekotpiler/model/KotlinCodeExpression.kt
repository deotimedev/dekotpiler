package me.deo.dekotpiler.model

import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.visitor.GenericVisitor
import com.github.javaparser.ast.visitor.VoidVisitor
import me.deo.dekotpiler.processing.Code

class KotlinCodeExpression(
    val code: Code
) : Expression() {
    override fun <R : Any?, A : Any?> accept(v: GenericVisitor<R, A>?, arg: A) = null
    override fun <A : Any?> accept(v: VoidVisitor<A>?, arg: A) = Unit
}
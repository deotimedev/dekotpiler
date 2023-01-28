package me.deo.dekotpiler.processing

import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.Statement
import kotlin.reflect.KClass

interface StatementTranslator<S : Statement> : Translator<S> {
    // Could technically resolve this by parameterized type but
    // that feels a little too magicky
    val type: KClass<S>
}
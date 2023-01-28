package me.deo.dekotpiler.processing

import com.github.javaparser.ast.expr.Expression
import kotlin.reflect.KClass

interface ExpressionTranslator<E : Expression> : Translator<E> {
    // Could technically resolve this by parameterized type but
    // that feels a little too magicky
    val type: KClass<E>
}
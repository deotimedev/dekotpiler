package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.expr.ConditionalExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class VariableDeclarationExpressionTranslator : ExpressionTranslator<VariableDeclarationExpr> {
    override val type = VariableDeclarationExpr::class

    override fun Translator.Context.translate(value: VariableDeclarationExpr) = buildString {
        append(if (value.isFinal) "val" else "var", " ")
        // don't think multi-variable declaration can happen in decompiled kotlin
        val variable = value.variables.first.get()

        append(variable.nameAsString)
        variable.initializer.getOrNull()?.let { initializer ->
            append(" = ") // be aware of delegation in the future
            append(translateExpression(initializer))
        } ?: append(": ${variable.type.asString()}")

    }
}
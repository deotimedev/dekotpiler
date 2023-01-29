package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.expr.ConditionalExpr
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import me.deo.dekotpiler.hints.UninitializedVariableHint
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class VariableDeclarationExpressionTranslator : ExpressionTranslator<VariableDeclarationExpr> {
    override val type = VariableDeclarationExpr::class

    override fun Translator.Context.translate(value: VariableDeclarationExpr) = codeWriter {
        write(if (value.isFinal) "val" else "var")
        write(" ")
        // don't think multi-variable declaration can happen in decompiled kotlin
        val variable = value.variables.first.get()

        write(variable.nameAsString)
        variable.initializer.getOrNull()?.let { initializer ->
            write(" = ") // be aware of delegation in the future
            writeExpression(initializer)
        } ?: run {
            write(": ${variable.type.asString()}")
            update(UninitializedVariableHint) { it + (variable.nameAsString to (currentIndex to value)) }
        }
    }
}
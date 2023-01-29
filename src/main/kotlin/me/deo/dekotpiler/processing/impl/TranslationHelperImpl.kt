package me.deo.dekotpiler.processing.impl

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.Statement
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.TranslationHelper
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import me.deo.dekotpiler.processing.provided.MethodTranslator
import me.deo.dekotpiler.processing.provided.ParameterTranslator
import org.springframework.stereotype.Component

@Component
class TranslationHelperImpl(
    private val methodTranslator: MethodTranslator,
    private val parameterTranslator: ParameterTranslator,
    expressionTranslators: List<ExpressionTranslator<*>>,
    statementTranslators: List<StatementTranslator<*>>
) : TranslationHelper {
    val expressionTranslatorsByType = expressionTranslators.associateBy { it.type.java }
    val statementTranslatorsByType = statementTranslators.associateBy { it.type.java }

    @Suppress("UNCHECKED_CAST")
    override fun Translator.Context.translateExpression(expression: Expression) =
        (expressionTranslatorsByType[expression::class.java] as? ExpressionTranslator<Expression>)?.run {
            translate(expression).also { code += it }
        } ?: codeWriter { writeExpression(expression) }

    @Suppress("UNCHECKED_CAST")
    override fun Translator.Context.translateStatement(statement: Statement) =
        (statementTranslatorsByType[statement::class.java] as? StatementTranslator<Statement>)?.run {
            translate(statement).also { code += it }
        } ?: codeWriter { writeStatement(statement) }

    override fun Translator.Context.translateMethod(method: MethodDeclaration) = with(methodTranslator) { translate(method) }

    override fun Translator.Context.translateParameter(param: Parameter) = with(parameterTranslator) { translate(param) }
}
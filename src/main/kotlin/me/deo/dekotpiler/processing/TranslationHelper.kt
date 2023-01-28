package me.deo.dekotpiler.processing

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.Statement

interface TranslationHelper {
    fun Translator.Context.translateExpression(expression: Expression): String
    fun Translator.Context.translateStatement(statement: Statement): String
    fun Translator.Context.translateMethod(method: MethodDeclaration): String
    fun Translator.Context.translateParameter(param: Parameter): String
}
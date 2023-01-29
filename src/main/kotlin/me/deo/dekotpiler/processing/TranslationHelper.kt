package me.deo.dekotpiler.processing

import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.Statement

interface TranslationHelper {
    fun Translator.Context.translateExpression(expression: Expression): Code
    fun Translator.Context.translateStatement(statement: Statement): Code
    fun Translator.Context.translateMethod(method: MethodDeclaration): Code
    fun Translator.Context.translateParameter(param: Parameter): Code
}
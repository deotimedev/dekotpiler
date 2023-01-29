package me.deo.dekotpiler.hints

import com.github.javaparser.ast.expr.VariableDeclarationExpr
import me.deo.dekotpiler.processing.Translator

object UninitializedVariableHint : Translator.Context.Hint.Defaulted<Map<String, Pair<Int, VariableDeclarationExpr>>> {
    override fun Translator.Context.default(): Map<String, Pair<Int, VariableDeclarationExpr>> = mapOf()
}
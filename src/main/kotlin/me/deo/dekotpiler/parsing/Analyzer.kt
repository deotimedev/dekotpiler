package me.deo.dekotpiler.parsing

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.Expression

interface Analyzer {
    fun parse(code: String): CompilationUnit
}
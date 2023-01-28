package me.deo.dekotpiler.parsing

import com.github.javaparser.JavaParser
import com.github.javaparser.ParseProblemException
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.stmt.ExpressionStmt
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class AnalyzerImpl(
    private val parser: JavaParser
) : Analyzer {
    override fun parse(code: String) =
        parser.parse(code).let { res ->
            res.takeIf { it.isSuccessful }?.result?.getOrNull() ?: throw ParseProblemException(res.problems)
        }

}
package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.stmt.ExpressionStmt
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class ExpressionStatementTranslator : StatementTranslator<ExpressionStmt> {
    override val type = ExpressionStmt::class

    override fun Translator.Context.translate(value: ExpressionStmt) = translateExpression(value.expression)
}
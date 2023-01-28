package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.expr.ConditionalExpr
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class ConditionalExpressionTranslator : ExpressionTranslator<ConditionalExpr> {
    override val type = ConditionalExpr::class

    override fun Translator.Context.translate(value: ConditionalExpr) =
        "if (${translateExpression(value.condition)}) ${translateExpression(value.thenExpr)} else ${translateExpression(value.elseExpr)}"
}
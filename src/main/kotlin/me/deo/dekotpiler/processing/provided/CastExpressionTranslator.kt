package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.expr.CastExpr
import com.github.javaparser.ast.expr.ConditionalExpr
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component

@Component
class CastExpressionTranslator : ExpressionTranslator<CastExpr> {
    override val type = CastExpr::class

    override fun Translator.Context.translate(value: CastExpr) = buildString {
        append(translateExpression(value.expression))
        // kotlin will randomly put these casts everywhere
        if (value.typeAsString != "Object") append(" as ${value.typeAsString}")
    }

}
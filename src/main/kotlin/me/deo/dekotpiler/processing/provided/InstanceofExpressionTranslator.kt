package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.expr.CastExpr
import com.github.javaparser.ast.expr.ConditionalExpr
import com.github.javaparser.ast.expr.InstanceOfExpr
import me.deo.dekotpiler.processing.ExpressionTranslator
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import org.springframework.stereotype.Component

@Component
class InstanceofExpressionTranslator : ExpressionTranslator<InstanceOfExpr> {
    override val type = InstanceOfExpr::class

    override fun Translator.Context.translate(value: InstanceOfExpr) = codeWriter {
        write("${translateExpression(value.expression)} is ${value.typeAsString}")
    }

}
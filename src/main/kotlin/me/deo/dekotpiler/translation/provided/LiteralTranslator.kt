package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.springframework.stereotype.Component

@Component
class LiteralTranslator : Translator<Literal, KtExpression> {
    override val type = Literal::class
    override fun Translation.translation(value: Literal): KtExpression {
        val literal = value.value
        return when (literal.type) {
            TypedLiteral.LiteralType.Integer -> KtLiteral.Int(literal.intValue)
            TypedLiteral.LiteralType.String -> KtLiteral.String(literal.toString())
            TypedLiteral.LiteralType.Class -> KtJClassExpression(KtLiteral.Class(translateType(literal.classValue)))
            else -> KtLiteral.Something(literal.value)
        }
    }
}
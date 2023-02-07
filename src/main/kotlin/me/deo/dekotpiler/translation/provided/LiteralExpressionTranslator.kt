package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType
import org.springframework.stereotype.Component

@Component
class LiteralExpressionTranslator : Translator<Literal, KtExpression> {
    override val type = Literal::class
    override fun Translation.translation(value: Literal): KtExpression {
        val literal = value.value
        return when (literal.type) {
            TypedLiteral.LiteralType.Integer -> KtLiteral.Int(literal.intValue)
            TypedLiteral.LiteralType.Long -> KtLiteral.Long(literal.longValue)
            TypedLiteral.LiteralType.Float -> KtLiteral.Float(literal.floatValue)
            TypedLiteral.LiteralType.Double -> KtLiteral.Double(literal.doubleValue)
            TypedLiteral.LiteralType.String -> KtLiteral.String(literal.toString().drop(1).dropLast(1))
            TypedLiteral.LiteralType.Class -> KtJClassExpression(KtLiteral.Class(translateType(literal.classValue)))
            TypedLiteral.LiteralType.NullObject -> KtLiteral.Null
            else ->
                if (literal.inferredJavaType.rawType == RawJavaType.BOOLEAN) KtLiteral.Boolean(literal.boolValue)
                else throw UnsupportedOperationException("${literal.type} is not supported as a literal.")
        }
    }
}
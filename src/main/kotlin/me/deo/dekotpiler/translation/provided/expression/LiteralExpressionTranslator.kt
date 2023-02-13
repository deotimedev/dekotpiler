package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.util.left
import org.benf.cfr.reader.bytecode.analysis.parse.expression.Literal
import org.benf.cfr.reader.bytecode.analysis.parse.literal.TypedLiteral
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType
import org.springframework.stereotype.Component

@Component
class LiteralExpressionTranslator : Translator<Literal, KtExpression> {
    override val type = Literal::class
    override fun Translation.Session.translation(value: Literal): KtExpression {
        val literal = value.value
        val raw = literal.inferredJavaType.rawType
        if (raw == RawJavaType.BOOLEAN) return KtLiteral.Boolean(literal.boolValue)
        if (raw == RawJavaType.CHAR) return KtLiteral.Char(literal.intValue.toChar())
        return when (literal.type) {
            TypedLiteral.LiteralType.Integer -> KtLiteral.Int(literal.intValue)
            TypedLiteral.LiteralType.Long -> KtLiteral.Long(literal.longValue)
            TypedLiteral.LiteralType.Float -> KtLiteral.Float(literal.floatValue)
            TypedLiteral.LiteralType.Double -> KtLiteral.Double(literal.doubleValue)
            TypedLiteral.LiteralType.String -> KtLiteral.String(literal.toString().drop(1).dropLast(1))
            TypedLiteral.LiteralType.Class -> KtJClassExpression(left(KtLiteral.Class(translateType(literal.classValue))))
            TypedLiteral.LiteralType.NullObject -> KtLiteral.Null
            else -> throw UnsupportedOperationException("${literal.type} is not supported as a literal.")
        }
    }
}
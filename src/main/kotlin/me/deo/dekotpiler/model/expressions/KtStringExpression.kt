package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.codeWriter

class KtStringExpression(
    val elements: MutableList<Element>
) : KtExpression {
    override val type = KtType.String

    override fun writeCode() = codeWriter {
        +"\""
        for (element in elements) {
            when (element) {
                is Element.Literal -> +element.literal
                is Element.Template -> write("$", "{", element.expression, "}")
            }
        }
        +"\""
    }
    sealed interface Element {
        @JvmInline
        value class Literal(val literal: String) : Element

        @JvmInline
        value class Template(val expression: KtExpression) : Element
    }
}
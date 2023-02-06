package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.buildCode
import me.deo.dekotpiler.util.singleOf

class KtStringExpression(
    val elements: MutableList<Element>
) : KtExpression {
    override val type = KtType.String

    // TODO this should flatten templates that are
    // also string expressions as it makes technically
    // valid but illogical strings such as:
    // "Hello ${"${name}!"}
    // when it should be:
    // "Hello ${name}!"
    override fun code() = buildCode {
        +"\""
        for (element in compress()) {
            when (element) {
                is Element.Literal -> +element.literal
                is Element.Template -> write("$", "{", element.expression, "}")
            }
        }
        +"\""
    }

    fun compress(): List<Element> = elements.flatMap {
        ((it as? Element.Template)?.expression as? KtStringExpression)?.elements ?: singleOf(it)
    }

    sealed interface Element {
        @JvmInline
        value class Literal(val literal: String) : Element

        @JvmInline
        value class Template(val expression: KtExpression) : Element
    }
}
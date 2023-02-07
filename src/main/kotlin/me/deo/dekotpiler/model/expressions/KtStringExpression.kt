package me.deo.dekotpiler.model.expressions

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.translation.buildCode
import me.deo.dekotpiler.util.singleOf

data class KtStringExpression(
    val elements: MutableList<KtExpression>
) : KtExpression {
    override val type = KtType.String

    override fun code() = buildCode {
        +"\""
        for (element in compress()) {
            when (element) {
                is KtLiteral.String -> +element.value
                else -> write("$", "{", element, "}")
            }
        }
        +"\""
    }

    fun compress(): List<KtExpression> = elements.flatMap {
        (it as? KtStringExpression)?.elements ?: singleOf(it)
    }
}
package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.singleOf
import com.deotime.vision.visions

data class KtStringExpression(
    val elements: MutableList<KtExpression>,
) : KtExpression {
    override val type = KtType.String

    override val sight = visions(::elements)
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
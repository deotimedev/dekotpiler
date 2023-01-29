package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.util.CFRExpression
import kotlin.reflect.KClass

interface ExpressionTranslator<E : CFRExpression, K : KtExpression> : Translator<E, K> {
    // Could technically resolve this by parameterized type but
    // that feels a little too magicky
    val type: KClass<E>
}
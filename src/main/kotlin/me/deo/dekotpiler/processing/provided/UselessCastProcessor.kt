package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.matching.ValueMatcher
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class UselessCastProcessor :
    PreProcessor<KtCastExpression>,
    ClassMatcher<KtCastExpression> by UselessCastMatcher {

    override fun replace(value: KtCastExpression) = value.expression

    companion object {
        val UselessCastMatcher = ValueMatcher(KtCastExpression::cast, KtType.Any.nullable())
    }
}
package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class UselessCastProcessor :
    PreProcessor<KtCastExpression>,
    ClassMatcher<KtCastExpression> by
    KtType.Matcher(KtCastExpression::cast, KtType.Any.nullable()) {

    override fun replace(value: KtCastExpression) = value.expression
}
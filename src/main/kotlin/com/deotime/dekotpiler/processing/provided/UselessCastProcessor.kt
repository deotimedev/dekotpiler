package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.matching.ValueMatcher
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.expressions.KtCastExpression
import com.deotime.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class UselessCastProcessor :
    PreProcessor<KtCastExpression>,
    Matcher<KtCastExpression> by UselessCastMatcher {

    override fun replace(value: KtCastExpression) = value.expression

    companion object {
        val UselessCastMatcher = ValueMatcher(KtCastExpression::cast, KtType.Any.nullable())
    }
}
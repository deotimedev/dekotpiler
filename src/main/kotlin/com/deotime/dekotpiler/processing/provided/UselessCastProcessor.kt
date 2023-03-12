package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.matching.Matchers.and
import com.deotime.dekotpiler.matching.Matchers.or
import com.deotime.dekotpiler.model.expressions.KtCastExpression
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtType.Companion.usableAs
import com.deotime.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class UselessCastProcessor :
    PreProcessor<KtCastExpression>,
    Matcher<KtCastExpression> by UselessCastMatcher {

    override fun replace(value: KtCastExpression) = value.expression

    companion object {
        val UselessCastMatcher = Matcher<KtCastExpression> { expression.type usableAs cast }
    }
}
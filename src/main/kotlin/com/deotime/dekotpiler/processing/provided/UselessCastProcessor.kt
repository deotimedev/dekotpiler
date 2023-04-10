package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.model.expressions.KtCastExpression
import com.deotime.dekotpiler.model.type.KtType.Companion.usableAs
import com.deotime.dekotpiler.processing.PreProcessor
import org.koin.core.annotation.Single

@Single
class UselessCastProcessor :
    PreProcessor<KtCastExpression>,
    Matcher<KtCastExpression> by UselessCastMatcher {

    override fun replace(value: KtCastExpression) = value.expression

    companion object {
        val UselessCastMatcher = Matcher<KtCastExpression> {
            expression.type usableAs cast
        }
    }
}
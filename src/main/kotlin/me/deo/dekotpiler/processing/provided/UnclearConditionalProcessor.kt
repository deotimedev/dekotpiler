package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.matching.ClassMatcher
import me.deo.dekotpiler.matching.Matcher
import me.deo.dekotpiler.matching.ValueMatcher
import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component


@Component
class UnclearConditionalProcessor :
    PreProcessor<KtConditional>,
    ClassMatcher<KtConditional> by UnclearConditionalMatcher {

    override fun modify(value: KtConditional) {
        value.joined!!.apply {
            operation = KtConditional.Operation.And
            conditional.inverse = false
        }
        value.inverse = false
    }

    companion object {
        val UnclearConditionalMatcher = ClassMatcher<KtConditional>() + Matcher {
            joined?.let { join ->
                join.operation == KtConditional.Operation.Or &&
                        (inverse && join.conditional.inverse)
            } ?: false
        }
    }
}
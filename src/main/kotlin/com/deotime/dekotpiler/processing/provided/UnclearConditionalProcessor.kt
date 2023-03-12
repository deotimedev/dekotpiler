package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.matching.Matcher
import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

// todo this is broken
// or maybe conditionals are just broken?
//@Component
//class UnclearConditionalProcessor :
//    PreProcessor<KtConditional>,
//    Matcher<KtConditional> by UnclearConditionalMatcher {
//
//    override fun modify(value: KtConditional) {
//        value.joined!!.apply {
//            operation = KtConditional.Operation.And
//            conditional.inverse = false
//        }
//        value.inverse = false
//    }
//
//    companion object {
//        val UnclearConditionalMatcher = Matcher<KtConditional> {
//            joined?.let { join ->
//                join.operation == KtConditional.Operation.Or &&
//                        (inverse && join.conditional.inverse)
//            } ?: false
//        }
//    }
//}
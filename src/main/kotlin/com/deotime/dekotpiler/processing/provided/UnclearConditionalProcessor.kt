package com.deotime.dekotpiler.processing.provided

// fixme
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
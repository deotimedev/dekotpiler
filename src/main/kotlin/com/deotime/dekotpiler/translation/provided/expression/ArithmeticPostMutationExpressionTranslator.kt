package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.arithmetic.KtArithmeticPostMutationExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticPostMutationOperation
import org.springframework.stereotype.Component

@Component
class ArithmeticPostMutationExpressionTranslator :
    Translator<ArithmeticPostMutationOperation, KtArithmeticPostMutationExpression> {

    override fun Translation.Session.translation(value: ArithmeticPostMutationOperation) =
        KtArithmeticPostMutationExpression(
            translateVariable(value.updatedLValue),
            value.op
        )
}
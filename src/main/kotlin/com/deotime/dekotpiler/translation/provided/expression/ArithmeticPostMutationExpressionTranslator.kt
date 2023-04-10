package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.arithmetic.KtArithmeticPostMutationExpression
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticPostMutationOperation
import org.koin.core.annotation.Single

@Single
class ArithmeticPostMutationExpressionTranslator :
    Translator<ArithmeticPostMutationOperation, KtArithmeticPostMutationExpression> {

    context (Translation.Session)
    override fun translation(value: ArithmeticPostMutationOperation) =
        KtArithmeticPostMutationExpression(
            translateVariable(value.updatedLValue),
            value.op
        )
}
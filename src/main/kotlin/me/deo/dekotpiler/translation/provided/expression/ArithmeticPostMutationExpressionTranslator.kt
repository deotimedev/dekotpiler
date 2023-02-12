package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.expressions.KtCastExpression
import me.deo.dekotpiler.model.expressions.arithmetic.KtArithmeticPostMutationExpression
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArithmeticPostMutationOperation
import org.benf.cfr.reader.bytecode.analysis.parse.expression.CastExpression
import org.springframework.stereotype.Component

@Component
class ArithmeticPostMutationExpressionTranslator : Translator<ArithmeticPostMutationOperation, KtArithmeticPostMutationExpression> {
    override val type = ArithmeticPostMutationOperation::class
    override fun Translation.Session.translation(value: ArithmeticPostMutationOperation) =
        KtArithmeticPostMutationExpression(
            translateVariable(value.updatedLValue),
            value.op
        )
}
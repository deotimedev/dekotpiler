package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.expressions.invoke.KtComparison
import com.deotime.dekotpiler.model.statements.KtForStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import com.deotime.dekotpiler.model.expressions.KtRangeExpression
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredFor
import org.springframework.stereotype.Component

@Component
class ForLoopStatementTranslator : Translator<StructuredFor, KtForStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredFor) = KtForStatement(
        translateVariable(value.initial.createdLValue),
        KtRangeExpression(
            translateExpression(value.initial.rValue),
            (translateConditional(value.condition) as KtComparison).comparing, // to my knowledge kotlin only generates a for loop with ints but i may be wrong
        ),
        translateBlock(value.body)
    )
}
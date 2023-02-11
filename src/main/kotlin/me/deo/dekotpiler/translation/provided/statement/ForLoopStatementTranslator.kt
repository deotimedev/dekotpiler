package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.invoke.KtComparisonInvoke
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.statements.KtForStatement
import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import me.deo.dekotpiler.translation.provided.expression.ComparisonOperationTranslator
import me.deo.dekotpiler.translation.provided.expression.KtRangeExpression
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredFor
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIter
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class ForLoopStatementTranslator : Translator<StructuredFor, KtForStatement> {
    override val type = StructuredFor::class
    override fun Translation.Session.translation(value: StructuredFor) = KtForStatement(
        translateVariable(value.initial.createdLValue),
        KtRangeExpression(
            translateExpression(value.initial.rValue),
            ((translateConditional(value.condition) as KtComparisonInvoke)
                .comparing as KtLiteral.Int).also { it.value-- }, // to my knowledge kotlin only generates a for loop with ints but i may be wrong
        ),
        translateBlock(value.body)
    )
}
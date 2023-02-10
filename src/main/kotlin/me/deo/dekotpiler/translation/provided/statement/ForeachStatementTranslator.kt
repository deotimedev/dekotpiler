package me.deo.dekotpiler.translation.provided.statement

import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.statements.KtForStatement
import me.deo.dekotpiler.model.statements.KtWhileStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredIter
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredWhile
import org.springframework.stereotype.Component

@Component
class ForeachStatementTranslator : Translator<StructuredIter, KtForStatement> {
    override val type = StructuredIter::class
    override fun Translation.translation(value: StructuredIter) = KtForStatement(
        translateVariable(value.iterator),
        translateExpression(value.list),
        translateBlock(value.body)
    )
}
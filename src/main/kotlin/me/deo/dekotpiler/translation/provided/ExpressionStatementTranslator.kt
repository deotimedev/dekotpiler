package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredExpressionStatement
import org.springframework.stereotype.Component

@Component
class ExpressionStatementTranslator : Translator<StructuredExpressionStatement, KtExpression> {
    override val type = StructuredExpressionStatement::class
    // does this need a seperate statement?... maybe
    override fun Translation.translation(value: StructuredExpressionStatement): KtExpression = translateExpression(value.expression)
}
package me.deo.dekotpiler.translation.provided

import me.deo.dekotpiler.model.statements.KtCommentStatement
import me.deo.dekotpiler.translation.StatementTranslator
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.springframework.stereotype.Component

@Component
class CommentStatementTranslator : StatementTranslator<StructuredComment, KtCommentStatement> {
    override val type = StructuredComment::class
    override fun Translation.translation(value: StructuredComment) =
        KtCommentStatement("comment") // todo fix whatevers going on here (cant get comment value from structured for some reason)
}
package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.statements.KtCommentStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.structured.statement.StructuredComment
import org.koin.core.annotation.Single

@Single
class CommentStatementTranslator : Translator<StructuredComment, KtCommentStatement> {

    context (Translation.Session)
    override fun translation(value: StructuredComment) =
        KtCommentStatement(translateExpression(value.expression).toString()) // todo fix whatevers going on here (cant get comment value from structured for some reason)
}
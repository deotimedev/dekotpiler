package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.translation.codeOf

data class KtCommentStatement(
    val comment: String
) : KtStatement {
    override fun code() = codeOf("// $comment")
}
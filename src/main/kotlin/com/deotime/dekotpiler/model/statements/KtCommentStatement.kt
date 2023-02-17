package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.coding.codeOf

data class KtCommentStatement(
    val comment: String
) : KtStatement {
    override fun code() = codeOf("// $comment")
}
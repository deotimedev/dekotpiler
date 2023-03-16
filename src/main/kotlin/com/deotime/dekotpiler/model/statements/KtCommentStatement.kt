package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.codeOf
import com.deotime.dekotpiler.model.KtStatement

data class KtCommentStatement(
    val comment: String,
) : KtStatement {
    override fun code() = codeOf("// $comment")
}
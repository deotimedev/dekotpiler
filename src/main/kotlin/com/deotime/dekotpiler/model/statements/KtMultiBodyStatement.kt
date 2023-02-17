package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.model.KtStatement

interface KtMultiBodyStatement : KtStatement {
    val bodies: List<KtBlockStatement>
}
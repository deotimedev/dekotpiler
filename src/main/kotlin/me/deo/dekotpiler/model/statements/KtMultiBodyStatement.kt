package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement

interface KtMultiBodyStatement : KtStatement {
    val bodies: List<KtBlockStatement>
}
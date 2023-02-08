package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.util.singleOf

interface KtBodyStatement : KtStatement {
    val body: KtStatement? get() = null
    val bodies: List<KtStatement> get() = listOfNotNull(body)
}
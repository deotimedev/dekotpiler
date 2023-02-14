package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.type.KtType

interface KtSingleBodyStatement : KtMultiBodyStatement {
    val body: KtBlockStatement
    override val bodies: List<KtBlockStatement>
        get() = listOf(body)

    // This should work for certain multibody statements like if else
    // but needs to be a way to get common type
    override val type: KtType
        get() = body.type
}
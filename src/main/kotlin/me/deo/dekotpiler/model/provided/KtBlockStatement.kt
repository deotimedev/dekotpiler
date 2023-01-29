package me.deo.dekotpiler.model.provided

import me.deo.dekotpiler.model.KtStatement

data class KtBlockStatement(
    val statements: List<KtStatement>
) : KtStatement
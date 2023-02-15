package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.model.type.KtTyped

interface KtStatement : KtTyped, Codable, KtExpressionViewable {
    override val type: KtType
        get() = KtType.Unit


}
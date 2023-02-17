package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.Codable
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtTyped

interface KtStatement : KtTyped, Codable, KtExpressionViewable {
    override val type: KtType
        get() = KtType.Unit


}
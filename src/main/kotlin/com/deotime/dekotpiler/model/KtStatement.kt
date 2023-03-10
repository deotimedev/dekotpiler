package com.deotime.dekotpiler.model

import com.deotime.dekotpiler.coding.Codable
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.model.type.KtTyped
import com.deotime.vision.Eyes

interface KtStatement : KtTyped, Codable, Eyes<KtExpression> {
    override val type: KtType
        get() = KtType.Unit
}
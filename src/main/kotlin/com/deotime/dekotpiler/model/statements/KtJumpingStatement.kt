package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.type.KtNothingType

interface KtJumpingStatement : KtStatement, KtOptionalLabel {
    override val type get() = KtNothingType
}
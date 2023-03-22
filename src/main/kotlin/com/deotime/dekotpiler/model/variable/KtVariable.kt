package com.deotime.dekotpiler.model.variable

import com.deotime.dekotpiler.model.KtExpression

interface KtVariable : KtExpression {

    val name: String
    var final: Boolean
    val synthetic: Boolean

}
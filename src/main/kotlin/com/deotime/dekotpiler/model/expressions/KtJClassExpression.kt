package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression

import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.Either
import com.deotime.dekotpiler.util.left
import com.deotime.dekotpiler.util.unwrap
import com.deotime.vision.Vision

data class KtJClassExpression(
    var clazz: Either<KtLiteral.Class, KtGetDynamicKClass>
) : KtExpression {
    override val sight: Vision<KtExpression> = TODO("this class needs to be changed")
    override val type get() = KtType.JClass
    override fun code() = buildCode {
        write(clazz.unwrap<KtExpression>(), ".java")
    }
}
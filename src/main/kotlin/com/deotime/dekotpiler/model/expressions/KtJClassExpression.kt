package com.deotime.dekotpiler.model.expressions

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtExpression
import com.deotime.dekotpiler.model.KtExpressionView
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.Either
import com.deotime.dekotpiler.util.left
import com.deotime.dekotpiler.util.unwrap
import com.deotime.dekotpiler.util.viewEither

data class KtJClassExpression(
    var clazz: Either<KtLiteral.Class, KtGetDynamicKClass>
) : KtExpression {
    override val expressionView: KtExpressionView =
        listOf(left(viewEither(::clazz)))
    override val type get() = KtType.JClass
    override fun code() = buildCode {
        write(clazz.unwrap<KtExpression>(), ".java")
    }
}
package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.model.expressions.KtGetDynamicKClass
import com.deotime.dekotpiler.model.expressions.KtJClassExpression
import com.deotime.dekotpiler.model.expressions.invoke.KtGetterInvoke
import com.deotime.dekotpiler.processing.PreProcessor
import org.koin.core.annotation.Single

@Single
class GetClassProcessor :
    PreProcessor<KtGetterInvoke> {

    override fun KtGetterInvoke.match() = name == "class"
    override fun replace(value: KtGetterInvoke) =
        KtJClassExpression.Dynamic(KtGetDynamicKClass(value.reference))

}
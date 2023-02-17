package com.deotime.dekotpiler.processing.provided

import com.deotime.dekotpiler.model.expressions.KtGetDynamicKClass
import com.deotime.dekotpiler.model.expressions.KtJClassExpression
import com.deotime.dekotpiler.model.expressions.invoke.KtGetterInvoke
import com.deotime.dekotpiler.processing.PreProcessor
import com.deotime.dekotpiler.util.right
import org.springframework.stereotype.Component

@Component
class GetClassProcessor :
    PreProcessor<KtGetterInvoke> {

    override fun KtGetterInvoke.match() = name == "class"
    override fun replace(value: KtGetterInvoke) =
        KtJClassExpression(right(KtGetDynamicKClass(value.reference)))

}
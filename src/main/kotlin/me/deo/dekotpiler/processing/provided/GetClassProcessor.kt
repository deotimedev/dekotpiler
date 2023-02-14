package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.expressions.KtGetDynamicKClass
import me.deo.dekotpiler.model.expressions.KtJClassExpression
import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.processing.PreProcessor
import me.deo.dekotpiler.util.right
import org.springframework.stereotype.Component

@Component
class GetClassProcessor :
    PreProcessor<KtGetterInvoke> {

    override fun KtGetterInvoke.match() = name == "class"
    override fun replace(value: KtGetterInvoke) =
        KtJClassExpression(right(KtGetDynamicKClass(value.reference)))

}
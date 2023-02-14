package me.deo.dekotpiler.processing.provided

import me.deo.dekotpiler.model.expressions.invoke.KtGetterInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtMethodInvoke
import me.deo.dekotpiler.model.expressions.invoke.KtSetterInvoke
import me.deo.dekotpiler.processing.PreProcessor
import org.springframework.stereotype.Component

@Component
class FieldFacadeProcessor :
    PreProcessor<KtMethodInvoke> {

    override fun KtMethodInvoke.match() = true
    override fun replace(value: KtMethodInvoke) =
        // TODO multiclass processing to confirm if these are actually field facades
        if (value.name.startsWith("get") && value.args.isEmpty()) KtGetterInvoke(value)
        else if (value.name.startsWith("set") && value.args.size == 1) KtSetterInvoke(value)
        else value
}
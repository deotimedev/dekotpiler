package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NotOperation
import org.springframework.stereotype.Component

@Component
class NotOperationTranslator : Translator<NotOperation, KtConditional> {
    override val type = NotOperation::class
    override fun Translation.translation(value: NotOperation) =
        translateConditional(value.inner).apply { inverse = true }
}
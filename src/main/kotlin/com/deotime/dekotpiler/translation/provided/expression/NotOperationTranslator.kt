package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NotOperation
import org.springframework.stereotype.Component

@Component
class NotOperationTranslator : Translator<NotOperation, KtConditional> {

    override fun Translation.Session.translation(value: NotOperation) =
        translateConditional(value.inner).apply { inverse = true }
}
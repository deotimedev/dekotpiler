package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.conditional.KtConditional
import com.deotime.dekotpiler.model.expressions.conditional.KtConditionalLike
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NotOperation
import org.koin.core.annotation.Single

@Single
class NotOperationTranslator : Translator<NotOperation, KtConditional> {

    context (Translation.Session)
    override fun translation(value: NotOperation) =
        KtConditionalLike(translateConditional(value.inner)).apply { inverse = true }
}
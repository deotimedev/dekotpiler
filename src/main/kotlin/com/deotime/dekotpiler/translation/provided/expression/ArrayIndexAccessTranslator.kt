package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtArrayIndexAccess
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArrayIndex
import org.springframework.stereotype.Component

@Component
class ArrayIndexAccessTranslator : Translator<ArrayIndex, KtArrayIndexAccess> {

    context (Translation.Session)
    override fun translation(value: ArrayIndex) = KtArrayIndexAccess(
        translateExpression(value.array),
        translateExpression(value.index)
    )
}
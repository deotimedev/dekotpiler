package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.KtConditional
import com.deotime.dekotpiler.model.expressions.KtArrayCreation
import com.deotime.dekotpiler.model.expressions.KtArrayIndexAccess
import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.ArrayIndex
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray
import org.springframework.stereotype.Component

@Component
class ArrayIndexAccessTranslator : Translator<ArrayIndex, KtArrayIndexAccess> {
    
    override fun Translation.Session.translation(value: ArrayIndex) = KtArrayIndexAccess(
        translateExpression(value.array),
        translateExpression(value.index)
    )
}
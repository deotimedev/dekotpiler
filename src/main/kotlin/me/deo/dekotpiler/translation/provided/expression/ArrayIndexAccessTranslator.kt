package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.expressions.KtArrayCreation
import me.deo.dekotpiler.model.expressions.KtArrayIndexAccess
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
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
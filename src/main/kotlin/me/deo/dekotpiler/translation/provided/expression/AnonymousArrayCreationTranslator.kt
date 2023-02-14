package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.KtConditional
import me.deo.dekotpiler.model.expressions.KtArrayCreation
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.BooleanExpression
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray
import org.springframework.stereotype.Component

@Component
class AnonymousArrayCreationTranslator : Translator<NewAnonymousArray, KtArrayCreation> {
    
    override fun Translation.Session.translation(value: NewAnonymousArray) = KtArrayCreation(
        translateArrayType(value.inferredJavaType),
        KtLiteral.Int(value.values.size),
        value.values.map(::translateExpression).toMutableList()
    )
}
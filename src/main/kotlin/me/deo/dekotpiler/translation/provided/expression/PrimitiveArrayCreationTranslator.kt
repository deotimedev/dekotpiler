package me.deo.dekotpiler.translation.provided.expression

import me.deo.dekotpiler.model.expressions.KtArrayCreation
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewObjectArray
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewPrimitiveArray
import org.springframework.stereotype.Component

@Component
class PrimitiveArrayCreationTranslator : Translator<NewPrimitiveArray, KtArrayCreation> {
    override val type = NewPrimitiveArray::class
    override fun Translation.Session.translation(value: NewPrimitiveArray) = KtArrayCreation(
        translateArrayType(value.inferredJavaType),
        translateExpression(value.getDimSize(0))
    )
}
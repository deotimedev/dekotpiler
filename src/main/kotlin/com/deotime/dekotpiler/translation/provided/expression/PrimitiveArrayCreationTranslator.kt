package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtArrayCreation
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewAnonymousArray
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewObjectArray
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewPrimitiveArray
import org.springframework.stereotype.Component

@Component
class PrimitiveArrayCreationTranslator : Translator<NewPrimitiveArray, KtArrayCreation> {
    
    override fun Translation.Session.translation(value: NewPrimitiveArray) = KtArrayCreation(
        translateArrayType(value.inferredJavaType),
        translateExpression(value.getDimSize(0))
    )
}
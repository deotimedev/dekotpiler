package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtArrayCreation
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewObjectArray
import org.springframework.stereotype.Component

@Component
class ObjectArrayCreationTranslator : Translator<NewObjectArray, KtArrayCreation> {

    context (Translation.Session)
    override fun translation(value: NewObjectArray) = KtArrayCreation(
        translateArrayType(value.inferredJavaType),
        translateExpression(value.getDimSize(0))
    )
}
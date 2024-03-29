package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtArrayCreation
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.parse.expression.NewPrimitiveArray
import org.koin.core.annotation.Single

@Single
class PrimitiveArrayCreationTranslator : Translator<NewPrimitiveArray, KtArrayCreation> {

    context (Translation.Session)
    override fun translation(value: NewPrimitiveArray) = KtArrayCreation(
        translateArrayType(value.inferredJavaType),
        translateExpression(value.getDimSize(0))
    )
}
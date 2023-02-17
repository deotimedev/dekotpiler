package com.deotime.dekotpiler.translation.provided.expression

import com.deotime.dekotpiler.model.expressions.KtArrayCreation
import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
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
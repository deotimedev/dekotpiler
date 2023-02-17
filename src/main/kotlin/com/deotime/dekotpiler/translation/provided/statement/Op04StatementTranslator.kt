package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.springframework.stereotype.Component

@Component
class Op04StatementTranslator : Translator<Op04StructuredStatement, KtStatement> {

    override fun Translation.Session.translation(value: Op04StructuredStatement): KtStatement =
        translateStatement(value.statement)
}
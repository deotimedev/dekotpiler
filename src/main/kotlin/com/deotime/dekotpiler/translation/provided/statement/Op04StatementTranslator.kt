package com.deotime.dekotpiler.translation.provided.statement

import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.translation.Translation
import com.deotime.dekotpiler.translation.Translator
import org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement
import org.koin.core.annotation.Single

@Single
class Op04StatementTranslator : Translator<Op04StructuredStatement, KtStatement> {

    context (Translation.Session)
    override fun translation(value: Op04StructuredStatement): KtStatement =
        translateStatement(value.statement)
}
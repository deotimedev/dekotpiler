package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.util.CFRStatement

interface StatementTranslator<S : CFRStatement, K : KtStatement> : Translator<S, K>
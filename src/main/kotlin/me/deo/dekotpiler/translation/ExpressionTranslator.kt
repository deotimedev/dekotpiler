package me.deo.dekotpiler.translation

import me.deo.dekotpiler.model.KtExpression
import me.deo.dekotpiler.util.CFRExpression

interface ExpressionTranslator<E : CFRExpression, K : KtExpression> : Translator<E, K>
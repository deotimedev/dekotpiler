package me.deo.dekotpiler.model

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.model.type.KtTyped

interface KtExpression : KtTyped, KtStatement, Codable
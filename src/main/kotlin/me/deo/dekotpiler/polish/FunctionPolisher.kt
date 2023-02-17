package me.deo.dekotpiler.polish

import me.deo.dekotpiler.model.structure.KtFunction

interface FunctionPolisher {
    fun polish(func: KtFunction)
}
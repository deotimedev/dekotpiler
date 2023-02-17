package com.deotime.dekotpiler.polish

import com.deotime.dekotpiler.model.structure.KtFunction

interface FunctionPolisher {
    fun polish(func: KtFunction)
}
package com.deotime.dekotpiler.translation

import com.deotime.dekotpiler.matching.Matcher

interface Translator<J : Any, K> : Matcher<J> {
    context (Translation.Session)
    fun translation(value: J): K
    override fun J.match() = true
}
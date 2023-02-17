package com.deotime.dekotpiler.translation

import com.deotime.dekotpiler.matching.Matcher

interface Translator<J : Any, K> : Matcher<J> {
    fun Translation.Session.translation(value: J): K
    override fun J.match() = true
}
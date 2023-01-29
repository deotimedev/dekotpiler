package me.deo.dekotpiler.translation

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.emptyCode

interface CodeWritable {
    fun writeCode(): Code = emptyCode()
}
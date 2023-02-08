package me.deo.dekotpiler.coding

import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.emptyCode

interface Codable {
    fun code(): Code = emptyCode()
}
package me.deo.dekotpiler.coding

import me.deo.dekotpiler.coding.Code
import me.deo.dekotpiler.coding.emptyCode

interface Codable {
    fun code(): Code = emptyCode()
}
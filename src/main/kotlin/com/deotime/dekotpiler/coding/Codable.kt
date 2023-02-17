package com.deotime.dekotpiler.coding

import com.deotime.dekotpiler.coding.Code
import com.deotime.dekotpiler.coding.emptyCode

interface Codable {
    fun code(): Code = emptyCode()
}
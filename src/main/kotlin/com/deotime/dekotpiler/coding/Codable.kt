package com.deotime.dekotpiler.coding

interface Codable {
    fun code(): Code = emptyCode()

    context (CodeBuilder)
    fun postfix() = Unit
}
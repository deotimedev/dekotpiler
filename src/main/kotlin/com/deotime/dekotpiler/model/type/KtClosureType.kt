package com.deotime.dekotpiler.model.type

import com.deotime.dekotpiler.coding.buildCode

data class KtClosureType internal constructor(
    val parameters: List<KtType>,
    val returns: KtType,
    val suspending: Boolean = false,
    val receiver: KtType? = null,
    override val nullable: Boolean = false
) : KtType {

    override val name = buildCode {
        fun closure() {
            if (suspending) +"suspend "
            receiver?.let { write(it, ".") }
            writeInvoker(parameters)
            write(" -> ", returns)
        }
        if (nullable) {
            braced { +closure() }
            +"?"
        } else +closure()
    }.toString()

    override fun nullable(nullable: Boolean) = copy(nullable = nullable)

}

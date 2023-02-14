package me.deo.dekotpiler.model.type

import me.deo.dekotpiler.coding.Codable
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.coding.codeOf
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

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

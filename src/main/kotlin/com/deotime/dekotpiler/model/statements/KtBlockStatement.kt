package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.util.flatMapInclusive

data class KtBlockStatement(
    val statements: MutableList<KtStatement>,
) : KtSingleBodyStatement {

    override val body = this

    override val type: KtType
        get() = statements.lastOrNull()?.type ?: KtType.Unit

    override fun code() = buildCode {
        statements.forEachIndexed { i, value ->
            if (i != 0) newline()
            write(value)
        }
    }

    fun flatten() = statements.flatMapInclusive {
        (it as? KtMultiBodyStatement)?.bodies.orEmpty()
    }

    companion object {
        fun KtStatement.asBlock() =
            if (this is KtBlockStatement) this else KtBlockStatement(mutableListOf(this))
    }

}
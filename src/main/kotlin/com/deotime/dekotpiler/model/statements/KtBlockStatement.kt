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
        // weird
        statements.fold(false) { prev, value ->
            if (prev) newline()
            write(value)
            true
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
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtType

data class KtBlockStatement(
    val statements: MutableList<KtStatement>
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

    companion object {
        fun KtStatement.asBlock() =
            if (this is KtBlockStatement) this else KtBlockStatement(mutableListOf(this))
    }

}
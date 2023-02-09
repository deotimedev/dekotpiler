package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.statements.KtBlockStatement.Companion.asBlock
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.singleOf

data class KtTryStatement(
    var statement: KtStatement,
    val catches: MutableList<Catch>,
    var finally: KtStatement?
) : KtMultiBodyStatement {

    override val type get() = statement.type

    override val bodies: List<KtBlockStatement>
        get() = (singleOf(statement) + catches.map { it.statement }).let { list ->
            finally?.let { list + it } ?: list
        }.map { it.asBlock() }

    override fun code() = buildCode {
        write("try")
        startBlock()
        write(statement)

        endBlock()
        catches.forEach { write(it) }
        finally?.let { finally ->
            write("finally")
            startBlock()
            write(finally)
            endBlock()
        }
    }

    data class Catch(
        var throwable: KtLocalVariable,
        var statement: KtStatement
    ) : KtStatement {
        override fun code() = buildCode {
            write("catch ")
            braced { write("${throwable.name}: ", throwable.type) }
            blocked { +statement }
        }
    }


}
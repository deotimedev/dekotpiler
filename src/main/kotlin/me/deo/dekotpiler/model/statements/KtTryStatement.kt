package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.singleOf

data class KtTryStatement(
    var statement: KtBlockStatement,
    val catches: MutableList<Catch>,
    var finally: KtBlockStatement?
) : KtMultiBodyStatement {

    override val type get() = statement.type

    override val bodies: List<KtBlockStatement>
        get() = (singleOf(statement) + catches.map { it.statement }).let { list ->
            finally?.let { list + it } ?: list
        }

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
        var throwable: KtLocalVariable, // it would not make sense for this to have an expression view
        var statement: KtBlockStatement
    ) : KtStatement {
        override fun code() = buildCode {
            write("catch ")
            braced { write("${throwable.name}: ", throwable.type) }
            blocked { +statement }
        }
    }


}
package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.coding.buildCode
import me.deo.dekotpiler.model.variable.KtLocalVariable

data class KtTryStatement(
    var statement: KtStatement,
    val catches: MutableList<Catch>,
    var finally: KtStatement?
) : KtStatement {

    override val type get() = statement.type

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
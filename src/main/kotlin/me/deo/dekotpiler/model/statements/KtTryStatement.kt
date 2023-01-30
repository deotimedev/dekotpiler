package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtLocal
import me.deo.dekotpiler.model.KtType
import me.deo.dekotpiler.model.KtTyped
import me.deo.dekotpiler.translation.codeWriter

data class KtTryStatement(
    var statement: KtStatement,
    val catches: MutableList<Catch>,
    var finally: KtStatement?
) : KtStatement {

    override val type get() = statement.type

    override fun writeCode() = codeWriter {
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
        var throwable: KtLocal,
        var statement: KtStatement
    ) : KtStatement {
        override fun writeCode() = codeWriter {
            write("catch ").braced("${throwable.name}: ", throwable.type)
            startBlock()
            write(statement)
            endBlock()
        }
    }


}
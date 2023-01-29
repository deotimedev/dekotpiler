package me.deo.dekotpiler.model.statements

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.KtVariable
import me.deo.dekotpiler.translation.Code
import me.deo.dekotpiler.translation.codeWriter
import org.benf.cfr.reader.bytecode.analysis.types.JavaTypeInstance
import org.benf.cfr.reader.bytecode.analysis.types.RawJavaType

data class KtTryStatement(
    var statement: KtStatement,
    val catches: MutableList<Catch>,
    var finally: KtStatement?
) : KtStatement {

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
        var throwable: KtVariable,
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
package com.deotime.dekotpiler.model.statements

import com.deotime.dekotpiler.coding.buildCode
import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.variable.KtLocalVariable
import com.deotime.dekotpiler.util.singleOf
import com.deotime.vision.vision

data class KtTryStatement(
    var statement: KtBlockStatement,
    val catches: MutableList<Catch>,
    var finally: KtBlockStatement?,
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
        var throwable: KtLocalVariable,
        var statement: KtBlockStatement,
    ) : KtStatement {
        override val sight = vision(::throwable)
        override fun code() = buildCode {
            write("catch ")
            braced { write("${throwable.name}: ", throwable.type) }
            blocked { +statement }
        }
    }


}
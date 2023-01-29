package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.stmt.BlockStmt
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.Translator
import me.deo.dekotpiler.processing.codeWriter
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class BlockStatementTranslator : StatementTranslator<BlockStmt> {
    override val type = BlockStmt::class

    override fun Translator.Context.translate(value: BlockStmt) = codeWriter {
        value.statements.forEach { write("${translateStatement(it)}\n") }
    }
}
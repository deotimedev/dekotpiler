package me.deo.dekotpiler.processing.provided

import com.github.javaparser.ast.stmt.BlockStmt
import me.deo.dekotpiler.processing.StatementTranslator
import me.deo.dekotpiler.processing.Translator
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class BlockStatementTranslator : StatementTranslator<BlockStmt> {
    override val type = BlockStmt::class

    override fun Translator.Context.translate(value: BlockStmt) = buildString {
        value.statements.forEach { append("${translateStatement(it)}\n") }
    }
}
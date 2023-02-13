package me.deo.dekotpiler.crawler.provided

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.matching.Matcher.Companion.match
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.KtNotNullAssertionExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.model.variable.KtLocalVariable
import org.benf.cfr.reader.bytecode.analysis.variables.NamedVariable
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

val NamedVariable.isGoodKotlinName
    // inline markers are our one saving grace when it comes
    // to detecting inlined closures, so dont remove them
    get() = isGoodName || stringName.startsWith("\$i")

@Component
class LocalVariableDeclarationCrawler : Crawler {
    override fun crawl(path: List<KtBlockStatement>) {
        val declared = mutableMapOf<String, KtVariableAssignmentStatement>()
        fun declaration(variable: KtLocalVariable) = declared[variable.name]
        path.forEach { block ->
            block.statements.removeIf { stmt ->
                if (stmt is KtVariableAssignmentStatement) {
                    var declaration: KtVariableAssignmentStatement? = null
                    if (stmt.declaring) {
                        declared[stmt.variable.name] = stmt
                        declaration = stmt
                    } else {
                        declared[stmt.variable.name]?.let {
                            declaration = it
                            declaration?.variable?.final = false
                        }
                    }

                    (declaration?.variable as? KtLocalVariable)?.apply {
                        if (stmt.expression == KtLiteral.Null)
                            type = type.nullable()
                        if (inlinable && !delegate.name.isGoodKotlinName) {
                            println("removing variable ${delegate.name}")
                            value = declaration?.expression
                            return@removeIf true
                        }
                    }

                } else if (stmt is KtStaticInvoke) {
                    // holding off on this check as there could potentially be useful
                    // information in the intrinsic message
                    if (false && NotNullCheckMatcher.match(stmt)) {
                        declaration((stmt.args[0] as KtLocalVariable))?.let { declaration ->
                            declaration.expression?.let {
                                declaration.expression = KtNotNullAssertionExpression(it)
                            }
                        }
                        return@removeIf true
                    }
                }
                false
            }
        }
    }

    companion object {
        val NotNullCheckMatcher = KtStaticInvoke.Matcher<Intrinsics>(
            "checkNotNull",
            "checkNotNullParameter"
        )
    }
}
package me.deo.dekotpiler.crawler.provided

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.matching.Matcher.Companion.match
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.KtNotNullAssertionExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.model.variable.KtLocalVariable
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

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
                    if (stmt.expression == KtLiteral.Null)
                        declaration?.variable?.apply { type = type.nullable() }
                } else if (stmt is KtStaticInvoke) {
                    if (NotNullCheckMatcher.match(stmt)) {
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
        val NotNullCheckMatcher = KtStaticInvoke.Matcher<Intrinsics>("checkNotNull")
    }
}
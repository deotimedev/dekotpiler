package me.deo.dekotpiler.crawler.provided

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.matching.Matcher.Companion.match
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.expressions.KtNotNullAssertionExpression
import me.deo.dekotpiler.model.expressions.invoke.KtStaticInvoke
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import me.deo.dekotpiler.model.variable.KtLocalVariable
import me.deo.dekotpiler.util.View
import me.deo.dekotpiler.util.unwrap
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

@Component
class LocalVariableDeclarationCrawler : Crawler {
    override fun crawl(path: List<KtBlockStatement>) {
        val declared = mutableMapOf<String, KtVariableAssignmentStatement>()
        fun declaration(variable: KtLocalVariable) = declared[variable.name]
        path.flatMap {
            it.flatten().flatMap { it.expressionView }
        }.forEach { expr ->
            println("Expr: ${expr.unwrap<View<*, *>>().get()}")
            expr
        }
        path.forEach { block ->
            block.statements.removeIf { stmt ->
                if (stmt is KtVariableAssignmentStatement) {
                    val variable = stmt.variable as? KtLocalVariable ?: return@removeIf false
                    var declaration: KtVariableAssignmentStatement? = null
                    if (stmt.declaring) {
                        declared[variable.name] = stmt
                        declaration = stmt
                    } else {
                        val expr = stmt.expression
//                        if (
//                            expr is KtIfElseExpression &&
//                            expr.then == KtLiteral.One && expr.orElse == KtLiteral.Zero
//                        ) {
//                            variable.inlineValues.push(
//                                expr.condition
//                            )
//                            variable.forceNextInline = true
//                            return@removeIf true
//                        }
                        declared[variable.name]?.let {
                            declaration = it
                            variable.final = false
                        }
                    }

                    (declaration?.variable as? KtLocalVariable)?.apply {
                        if (stmt.expression == KtLiteral.Null)
                            type = type.nullable()
                        if (inlinable && synthetic) {
                            println("removing variable $name")
                            declaration?.expression?.let { inlineValue = it }
                            return@removeIf true
                        }
                    }

                } else if (stmt is KtStaticInvoke) {
                    // holding off on this check as there could potentially be useful
                    // information in the intrinsic message
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
        val NotNullCheckMatcher = KtStaticInvoke.Matcher<Intrinsics>(
            "checkNotNull",
            "checkNotNullParameter"
        )
    }
}
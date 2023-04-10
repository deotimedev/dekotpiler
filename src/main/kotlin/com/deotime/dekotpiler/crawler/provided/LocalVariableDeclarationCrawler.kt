package com.deotime.dekotpiler.crawler.provided

import com.deotime.dekotpiler.crawler.Crawler
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.matching.Matchers.match
import com.deotime.dekotpiler.model.expressions.KtLiteral
import com.deotime.dekotpiler.model.expressions.KtNotNullAssertionExpression
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke.Companion.staticInvoke
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.model.statements.KtVariableAssignmentStatement
import com.deotime.dekotpiler.model.variable.KtLocalVariable
import org.koin.core.annotation.Single
import kotlin.jvm.internal.Intrinsics

// This is most likely just a temporary class, and should
// be reworked.
@Single
class LocalVariableDeclarationCrawler : Crawler {
    override fun crawl(path: List<KtBlockStatement>) {
        val declared = mutableMapOf<String, KtVariableAssignmentStatement>()
        fun declaration(variable: KtLocalVariable) = declared[variable.name]
        path.forEach { block ->
            block.statements.removeIf { stmt ->
                if (stmt is KtVariableAssignmentStatement) {
                    val variable = stmt.variable as? KtLocalVariable ?: return@removeIf false
                    var declaration: KtVariableAssignmentStatement? = null
                    if (stmt.declaring) {
                        declared[variable.name] = stmt
                        declaration = stmt
                    } else {
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
                    // This will likely be removed as useful information can be obtained
                    // through the intrinsic exception message
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
        val NotNullCheckMatcher = Matchers.staticInvoke<Intrinsics>(
            "checkNotNull",
            "checkNotNullParameter"
        )
    }
}
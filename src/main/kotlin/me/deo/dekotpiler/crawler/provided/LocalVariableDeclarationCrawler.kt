package me.deo.dekotpiler.crawler.provided

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.model.expressions.KtLiteral
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtVariableAssignmentStatement
import org.springframework.stereotype.Component

@Component
class LocalVariableDeclarationCrawler : Crawler {
    override fun crawl(path: List<KtBlockStatement>) {
        val declared = mutableMapOf<String, KtVariableAssignmentStatement>()
        path.forEach {
            it.statements.forEach { stmt ->
                if (stmt is KtVariableAssignmentStatement) {
                    ;
                    if (stmt.declaring) declared[stmt.variable.name] = stmt
                    else {
                        declared[stmt.variable.name]?.let { declaration ->
                            declaration.variable.final = false
                            if (stmt.expression == KtLiteral.Null)
                                declaration.variable.apply { type = type.nullable() }
                        }
                    }
                }
            }
        }
    }
}
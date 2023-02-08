package me.deo.dekotpiler.crawler

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.statements.KtBodyStatement

interface StatementCrawler {

    fun Context.crawl()

    interface Context {
        fun pop(statement: KtStatement)
        fun replace(old: KtStatement, new: KtStatement)
        fun find(condition: (KtStatement) -> Boolean, closure: (KtStatement) -> Unit)

        companion object {
            inline fun <reified T : KtStatement> Context.find(crossinline closure: (T) -> Unit) = find({ it is T }) {
                closure(it as T)
            }
        }
    }
}
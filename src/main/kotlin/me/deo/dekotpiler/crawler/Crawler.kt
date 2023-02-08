package me.deo.dekotpiler.crawler

import me.deo.dekotpiler.model.KtStatement

interface Crawler {

    fun Cursor.crawl()

    interface Cursor {
        val current: KtStatement
        fun next()
        fun pop()
        fun insert(statement: KtStatement)
        fun replace(statement: KtStatement)
        fun go(condition: (KtStatement) -> Boolean, closure: (KtStatement) -> Unit)


        companion object {
            inline fun <reified T : KtStatement> Cursor.go(
                crossinline condition: (T) -> Boolean = { true },
                crossinline closure: (T) -> Unit,
            ) =
                go({ it is T && condition(it) }) {
                    closure(it as T)
                }
        }
    }
}
package me.deo.dekotpiler.crawler.impl

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtMultiBodyStatement

internal class CrawlerCursorImpl(
    private val block: KtBlockStatement
) : Crawler.Cursor {
    private var dirty = false
    private var index = 0
    private var offset = 0
    private var lastBlock = block

    private var cacheMapped = indexMap(block)
    private val mapped
        get() = if (dirty) indexMap(block).also { cacheMapped = it } else cacheMapped

    override val current: KtStatement
        get() = mapped[index]?.statements.orEmpty()[offset]

    override fun next() {
        index++
        val new = mapped[index]!!
        println("i:$index:$offset new: $new")
        if (lastBlock !== new) {
            println("wow")
            offset = 0
            lastBlock = new
        } else offset++
    }

    override fun pop() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun insert(statement: KtStatement) {
        TODO("Not yet implemented")
    }

    override fun replace(statement: KtStatement) {
        TODO("Not yet implemented")
    }

    override fun go(condition: (KtStatement) -> Boolean, closure: (KtStatement) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun traverse(condition: (KtStatement) -> Boolean, closure: (KtStatement) -> Unit) {
        TODO("Not yet implemented")
    }

    private inline fun withDirty(closure: () -> Unit) {
        dirty = true
        closure()
    }

    private fun indexMap(block: KtBlockStatement, off: Int = 0): Map<Int, KtBlockStatement> =
        buildMap<Int, KtBlockStatement> {
            var i = 0
            block.statements.forEach { stmt ->
                if (stmt is KtMultiBodyStatement) {
                    println("MULTI: $stmt")
                    stmt.bodies.forEach {
                        putAll(indexMap(it, i))
                    }
                }
                else {
                    this[i] = block
                    println("STATEMENT PROC: ${stmt::class}")
//                    put(i, block) // this doesnt work for some reason????
                }
                i++
                println("indexss $off:${keys}")
            }
        }.mapKeys { (i, _) -> i + off }
}
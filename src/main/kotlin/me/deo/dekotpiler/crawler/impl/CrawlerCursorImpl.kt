package me.deo.dekotpiler.crawler.impl

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.statements.KtBlockStatement

internal class CrawlerCursorImpl(
    private val block: KtBlockStatement
) : Crawler.Cursor {
    private var dirty = false

    private var cachedFlatten = block.flatten()
    private val flattened get() =
        if (!dirty) cachedFlatten else block.flatten().also { cachedFlatten = it }

    private var index = 0

    override val current: KtStatement?
        get() = flattened.getOrNull(index)

    override fun next() {
        index++
    }

    private inline fun withDirty(closure: () -> Unit) {
        dirty = true
        closure()
    }
}
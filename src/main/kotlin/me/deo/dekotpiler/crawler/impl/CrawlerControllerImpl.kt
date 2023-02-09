package me.deo.dekotpiler.crawler.impl

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.crawler.CrawlerController
import me.deo.dekotpiler.model.statements.KtBlockStatement
import org.springframework.stereotype.Component

@Component
internal class CrawlerControllerImpl(
    override val crawlers: List<Crawler>
) : CrawlerController {
    override fun deploy(crawler: Crawler, block: KtBlockStatement) {
        val cursor = CrawlerCursorImpl(block)
        with(crawler) { cursor.crawl() }
    }
}
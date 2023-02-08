package me.deo.dekotpiler.crawler

import me.deo.dekotpiler.model.statements.KtBlockStatement

interface CrawlerController {
    val crawlers: List<Crawler>
    fun deploy(crawler: Crawler, block: KtBlockStatement)
}
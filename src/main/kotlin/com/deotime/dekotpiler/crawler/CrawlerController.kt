package com.deotime.dekotpiler.crawler

import com.deotime.dekotpiler.model.statements.KtBlockStatement

interface CrawlerController {
    val crawlers: List<Crawler>
    fun deploy(crawler: Crawler, block: KtBlockStatement)
}
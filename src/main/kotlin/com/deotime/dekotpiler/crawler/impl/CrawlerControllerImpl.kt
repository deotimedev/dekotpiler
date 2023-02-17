package com.deotime.dekotpiler.crawler.impl

import com.deotime.dekotpiler.crawler.Crawler
import com.deotime.dekotpiler.crawler.CrawlerController
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.model.statements.KtMultiBodyStatement
import org.springframework.stereotype.Component

@Component
internal class CrawlerControllerImpl(
    override val crawlers: List<Crawler>
) : CrawlerController {
    override fun deploy(crawler: Crawler, block: KtBlockStatement) {
        val paths = paths(block)
        paths.forEach {
            crawler.crawl(it)
        }
    }

    fun paths(thing: KtBlockStatement): List<List<KtBlockStatement>> = (listOf(listOf(thing)) + thing.statements.map { item ->
        when (item) {
            is KtMultiBodyStatement -> item.bodies.flatMap { paths(it) }.map { path ->
                listOf(thing) + path
            }
            else -> emptyList()
        }
    }.flatten())
}
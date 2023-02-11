package me.deo.dekotpiler.crawler.impl

import me.deo.dekotpiler.crawler.Crawler
import me.deo.dekotpiler.crawler.CrawlerController
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.statements.KtMultiBodyStatement
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
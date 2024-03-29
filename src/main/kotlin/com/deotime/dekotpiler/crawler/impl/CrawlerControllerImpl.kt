package com.deotime.dekotpiler.crawler.impl

import com.deotime.dekotpiler.crawler.Crawler
import com.deotime.dekotpiler.crawler.CrawlerController
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.model.statements.KtMultiBodyStatement
import org.koin.core.annotation.Single

@Single
internal class CrawlerControllerImpl(
    override val crawlers: List<Crawler>,
) : CrawlerController {

    override fun deploy(crawlers: List<Crawler>, block: KtBlockStatement) {
        val paths = paths(block)
        crawlers.forEach { crawler ->
            paths.forEach {
                crawler.crawl(it)
            }
        }
    }

    fun paths(thing: KtBlockStatement): List<List<KtBlockStatement>> =
        (listOf(listOf(thing)) + thing.statements.map { item ->
            when (item) {
                is KtMultiBodyStatement -> item.bodies.flatMap { paths(it) }.map { path ->
                    listOf(thing) + path
                }

                else -> emptyList()
            }
        }.flatten())
}
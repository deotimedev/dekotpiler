package me.deo.dekotpiler.crawler

import org.springframework.stereotype.Component

@Component
class TestCrawler : Crawler {
    override fun Crawler.Cursor.crawl() {
        while (true) {
            println("CURRENT: ${current}")
            next()
        }
    }
}
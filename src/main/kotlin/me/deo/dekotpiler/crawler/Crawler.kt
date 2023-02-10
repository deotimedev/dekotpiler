package me.deo.dekotpiler.crawler

import me.deo.dekotpiler.model.KtStatement
import me.deo.dekotpiler.model.statements.KtBlockStatement

interface Crawler {

    fun crawl(path: List<KtBlockStatement>)

}
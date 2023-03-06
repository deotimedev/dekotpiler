package com.deotime.dekotpiler.crawler

import com.deotime.dekotpiler.model.statements.KtBlockStatement

interface Crawler {

    val priority get() = 0
    fun crawl(path: List<KtBlockStatement>)

}
package com.deotime.dekotpiler.crawler

import com.deotime.dekotpiler.model.KtStatement
import com.deotime.dekotpiler.model.statements.KtBlockStatement

interface Crawler {

    fun crawl(path: List<KtBlockStatement>)

}
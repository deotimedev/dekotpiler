package com.deotime.dekotpiler.crawler.provided

import com.deotime.dekotpiler.crawler.Crawler
import com.deotime.dekotpiler.matching.Matchers
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke
import com.deotime.dekotpiler.model.expressions.invoke.KtStaticInvoke.Companion.staticInvoke
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import org.springframework.stereotype.Component
import kotlin.jvm.internal.Intrinsics

@Component
class IntrinsicProvidedCrawler : Crawler {
    override fun crawl(path: List<KtBlockStatement>) {
        path.forEach {
            it.statements
                .filterIsInstance<KtStaticInvoke>()
                .filter(IntrinsicProvidedMatcher)
                .forEach {
                    println("Intrinsic marker: ${it.code()}")
                }
        }
    }

    companion object {
        val IntrinsicProvidedMatcher = Matchers.staticInvoke<Intrinsics>("checkNotNullExpressionValue")
    }
}
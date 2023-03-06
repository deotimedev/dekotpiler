package com.deotime.dekotpiler.classfile.impl

import com.deotime.dekotpiler.classfile.ClassFileController
import com.deotime.dekotpiler.crawler.CrawlerController
import com.deotime.dekotpiler.crawler.provided.LocalVariableDeclarationCrawler
import com.deotime.dekotpiler.model.statements.KtBlockStatement
import com.deotime.dekotpiler.translation.Translation
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method
import org.springframework.stereotype.Component

@Component
internal class ClassFileControllerImpl(
    private val classWorker: ClassClassFileWorker,
    private val interfaceWorker: InterfaceClassFileWorker,
    private val translation: Translation,
    private val crawlerController: CrawlerController,
) : ClassFileController {
    override fun analyze(classFile: ClassFile): Map<Method, KtBlockStatement?> {
        val worker = when {
            classFile.isInterface -> interfaceWorker
            else -> classWorker
        }

        return worker.work(classFile).entries.associate { (method, statement) ->
            val block = statement?.let(translation.session()::translateBlock)
                ?.also { crawlerController.deploy(crawlerController.crawlers, it) }
            method to block
        }
    }
}
package me.deo.dekotpiler.classfile.impl

import me.deo.dekotpiler.classfile.ClassFileController
import me.deo.dekotpiler.crawler.CrawlerController
import me.deo.dekotpiler.crawler.provided.LocalVariableDeclarationCrawler
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.translation.Translation
import org.benf.cfr.reader.entities.ClassFile
import org.benf.cfr.reader.entities.Method
import org.springframework.stereotype.Component

@Component
internal class ClassFileControllerImpl(
    private val classWorker: ClassClassFileWorker,
    private val interfaceWorker: InterfaceClassFileWorker,
    private val translation: Translation,
    private val crawlerController: CrawlerController,
    private val localVariableCrawler: LocalVariableDeclarationCrawler // TODO REMOVE
) : ClassFileController {
    override fun analyze(classFile: ClassFile): Map<Method, KtBlockStatement?> {
        val worker = when {
            classFile.isInterface -> interfaceWorker
            else -> classWorker
        }

        return worker.work(classFile).entries.associate { (method, statement) ->
            val block = statement?.let(translation.session()::translateBlock)
                ?.also { crawlerController.deploy(localVariableCrawler, it) }
            method to block
        }
    }
}
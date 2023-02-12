package me.deo.dekotpiler

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.crawler.CrawlerController
import me.deo.dekotpiler.crawler.provided.LocalVariableDeclarationCrawler
import me.deo.dekotpiler.decompile.CFRDecompilerEngine
import me.deo.dekotpiler.decompile.DecompilerEngine
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.util.exportTasks
import me.deo.dekotpiler.util.getValue
import me.deo.dekotpiler.ui.FileSelector
import me.deo.dekotpiler.util.task
import me.deo.dekotpiler.util.taskAsync
import me.deotime.kpoetdsl.FunctionBuilder.Initializer.invoke
import me.deotime.kpoetdsl.metadata.toSpec
import org.springframework.boot.Banner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component
import java.io.File

@Component
class Main(
    private val metadataResolver: MetadataResolver,
    private val fileSelector: FileSelector,
    private val engine: DecompilerEngine,
    private val translation: Translation,
    private val crawlerController: CrawlerController,
    private val testCrawler: LocalVariableDeclarationCrawler
) : CommandLineRunner {
    // This will eventually be replaced by a CLI
    override fun run(vararg args: String): Unit = runBlocking(Dispatchers.Default) {

        // testing
        val file = File(File("").absolutePath, "/build/classes/kotlin/main/me/deo/dekotpiler/Test.class")

        val metadata by taskAsync("Metadata") { KotlinClassMetadata.read(metadataResolver.resolve(file)) as KotlinClassMetadata.Class }
        val clazz by taskAsync("CFR") { engine.decompile(file) ?: error("Couldn't read class.") }

        task("Kotlin Decompilation") {
            val metaClass = metadata.toKmClass()
            clazz.methods.forEach { cfrMethod ->
                val block = cfrMethod.analysis.statement.let { stmt ->
                    val translated = translation.session().translateStatement(stmt)
                    if ((translated as KtBlockStatement).statements.isEmpty()) return@forEach

                    println(translated.statements.lastOrNull()?.let { it::class })
                    translated
                }

                try {
                    crawlerController.deploy(testCrawler, block)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

//                val method = metaClass.functions.find { it.name == cfrMethod.name }?.toSpec()?.invoke {
//                    code {
//                        +block.code().toString()
//                    }
//                }
                println("------------${cfrMethod.name}---------------")
                println(block.code())
                println("------------${cfrMethod.name}---------------")

            }
        }
        // testing only
//        (metadata as KotlinClassMetadata.Class).let { meta ->
//            (meta.toKmClass().toSpec()) {
//                source.funSpecs.replaceAll { func ->
//                    func.invoke {
//                        code {
//
//                            // add code here
//                        }
//                    }
//                }
//            }
//        }

    }

    private companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            task("Spring Initialization") {
                SpringApplicationBuilder(App::class.java)
                    .web(WebApplicationType.NONE)
                    .bannerMode(Banner.Mode.OFF)
                    .logStartupInfo(false)
                    .headless(false)
                    .application()
            }.run()
            exportTasks()
        }

        @SpringBootApplication
        class App

    }
}
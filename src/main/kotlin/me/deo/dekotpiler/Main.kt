package me.deo.dekotpiler

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deo.dekotpiler.crawler.CrawlerController
import me.deo.dekotpiler.crawler.provided.LocalVariableDeclarationCrawler
import me.deo.dekotpiler.decompile.KotlinJarLoader
import me.deo.dekotpiler.metadata.MetadataResolver
import me.deo.dekotpiler.model.statements.KtBlockStatement
import me.deo.dekotpiler.model.type.KtType
import me.deo.dekotpiler.translation.Translation
import me.deo.dekotpiler.ui.FileSelector
import me.deo.dekotpiler.util.task
import me.deotime.kpoetdsl.FunctionBuilder.Initializer.invoke
import me.deotime.kpoetdsl.kotlin
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
    private val engine: KotlinJarLoader,
    private val translation: Translation,
    private val crawlerController: CrawlerController,
    private val testCrawler: LocalVariableDeclarationCrawler
) : CommandLineRunner {
    // This will eventually be replaced by a CLI
    override fun run(vararg args: String): Unit = runBlocking(Dispatchers.Default) {

        // testing
        val file = File(File("").absolutePath, "/build/libs/dekotpiler-1.0.0-plain.jar")

//        val file = fileSelector.selectFile(
//            "Select jar",
//            description = "Files",
//            "*.jar"
//        ) ?: return@runBlocking
//        val metadata = taskAsync("Metadata") { KotlinClassMetadata.read(metadataResolver.resolve(file)) as KotlinClassMetadata.Class }
        val jar = task("CFR") { engine.load(file) ?: error("Couldn't read class.") }

//        jar.types.forEach {
//            println("type: ${it.qualifiedName}")
//        }
        val target = KtType<Companion>()
        println("qualified: ${target.qualifiedName}")
        val metadata = jar.metadata(target) as KotlinClassMetadata.Class
        val clazz = jar.load(target)
        task("Kotlin Decompilation") {
            val metaClass = metadata.toKmClass()
            val methods = clazz?.methods.orEmpty().mapNotNull { cfrMethod ->
                val block = cfrMethod.analysis.statement.let { stmt ->
                    val translated = translation.session().translateStatement(stmt)
                    if ((translated as KtBlockStatement).statements.isEmpty()) return@mapNotNull null
                    translated
                }

                try {
                    crawlerController.deploy(testCrawler, block)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

                val method = metaClass.functions.find { it.name == cfrMethod.name }?.toSpec()?.invoke {
                    code {
                        +block.code().toString()
                    }
                }
//                println("------------${cfrMethod.name}---------------")
//                println(block.code())
//                println("------------${cfrMethod.name}---------------")
                method
            }
            val spec = metaClass.toSpec {
                source.funSpecs.clear()
                source.funSpecs.addAll(methods)
            }
            val output = kotlin {
                name("test") packaged "testing"
                +spec
            }
            println(output.properFormatting().withoutPublic())
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
//            exportTasks()
        }

        @SpringBootApplication
        open class App

    }
}
package com.deotime.dekotpiler

import com.deotime.dekotpiler.classfile.ClassFileController
import com.deotime.dekotpiler.jar.KotlinJarLoader
import com.deotime.dekotpiler.metadata.MetadataResolver
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.ui.FileSelector
import com.deotime.dekotpiler.util.context
import com.deotime.dekotpiler.util.task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deotime.kpoetdsl.FunctionBuilder.Initializer.invoke
import me.deotime.kpoetdsl.TypeKind.Scope.Companion.Interface
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
    private val classFileController: ClassFileController
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
        val target = KtType<Test>()
        println("qualified: ${target.qualifiedName}")
        val metadata = jar.metadata(target) as KotlinClassMetadata.Class
        val clazz = jar.load(target) ?: return@runBlocking
        task("Kotlin Decompilation") {
            val metaClass = metadata.toKmClass()
            val methods = classFileController.analyze(clazz)

            val specMethods = methods.mapNotNull { (cfr, block) ->
                metaClass.functions.find { it.name == cfr.name }?.toSpec()?.invoke {
                    code {
                        block?.code()?.let { +it.toString() }
                    }
                }
            }

            // todo wont work with objects and some other stuff because of synthetic constructors
            val spec = metaClass.toSpec {
                source.funSpecs.clear()
                source.funSpecs.addAll(specMethods)
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
            val app = task("Spring Initialization") {
                SpringApplicationBuilder(App::class.java)
                    .web(WebApplicationType.NONE)
                    .bannerMode(Banner.Mode.OFF)
                    .logStartupInfo(false)
                    .headless(false)
                    .application()
            }.run()
            context = app
//            exportTasks()
        }

        @SpringBootApplication
        open class App

    }
}
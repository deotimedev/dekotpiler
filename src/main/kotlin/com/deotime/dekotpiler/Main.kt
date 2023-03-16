package com.deotime.dekotpiler

import com.deotime.dekotpiler.classfile.ClassFileController
import com.deotime.dekotpiler.jar.KotlinJarLoader
import com.deotime.dekotpiler.jar.storage.KotlinJarFileLocator
import com.deotime.dekotpiler.metadata.MetadataResolver
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.ui.FileSelector
import com.deotime.dekotpiler.util.context
import com.deotime.dekotpiler.util.partitionNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
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
    private val classFileController: ClassFileController,
    private val locator: KotlinJarFileLocator,
) : CommandLineRunner {
    // This will eventually be replaced by a CLI
    override fun run(vararg args: String): Unit = runBlocking(Dispatchers.Default) {

        // for testing
        val file = File(File("").absolutePath, "/build/libs/dekotpiler-1.0.0-plain.jar")

//        val file = fileSelector.selectFile(
//            "Select jar",
//            description = "Files",
//            "*.jar"
//        ) ?: return@runBlocking
        val jar = engine.load(file)

        val target = KtType<Test>()
        println("qualified: ${target.qualifiedName}")
        val metadata = jar.metadata(target) as KotlinClassMetadata.Class
        val clazz = jar.load(target) ?: return@runBlocking
        val metaClass = metadata.toKmClass()
        val methods = classFileController.analyze(clazz)

        val (specMethods, synthetic) = methods.entries.partitionNotNull { (cfr, block) ->
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
            synthetic.forEach { (cfr, code) ->
                comment("")
                comment("<SYNTHETIC> ${cfr.methodPrototype}")
                comment("----------------")
                code?.code()?.let { comment(it.toString()) }
                comment("----------------")
            }
        }
        println(output.properFormatting().withoutPublic())

    }

    private companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplicationBuilder(App::class.java)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .headless(false)
                .application().run()
            context = app
        }

        @SpringBootApplication
        class App

    }
}
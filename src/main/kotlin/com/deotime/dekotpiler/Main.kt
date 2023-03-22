package com.deotime.dekotpiler

import com.deotime.dekotpiler.classfile.ClassFileController
import com.deotime.dekotpiler.jar.KotlinJarLoader
import com.deotime.dekotpiler.model.type.KtType
import com.deotime.dekotpiler.ui.FileSelector
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
    private val fileSelector: FileSelector,
    private val engine: KotlinJarLoader,
    private val classFileController: ClassFileController,
) : CommandLineRunner {
    // TODO: Command line interface for this
    override fun run(vararg args: String): Unit = runBlocking(Dispatchers.Default) {

        // for testing
        val file =
            if (args.getOrNull(0) == "customfile")
                fileSelector.selectFile(
                    "Select jar",
                    description = "Files",
                    "*.jar"
                ) ?: return@runBlocking
            else File(File("").absolutePath, "/build/libs/dekotpiler-1.0.0-plain.jar")

        val jar = engine.load(file)

        val target = KtType<Test>()
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
            SpringApplicationBuilder(App::class.java)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .headless(false)
                .application()
                .run()
        }

        @Suppress("RedundantModalityModifier") // don't have the allopen plugin for k2 currently
        @SpringBootApplication
        open class App

    }
}
package com.deotime.dekotpiler

import com.deotime.dekotpiler.classfile.ClassFileController
import com.deotime.dekotpiler.jar.KotlinJarLoader
import com.deotime.dekotpiler.model.type.KtReferenceType
import com.deotime.dekotpiler.ui.FileSelector
import com.deotime.dekotpiler.util.partitionNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.metadata.jvm.KotlinClassMetadata
import me.deotime.kpoetdsl.FunctionBuilder.Initializer.invoke
import me.deotime.kpoetdsl.kotlin
import me.deotime.kpoetdsl.metadata.toSpec
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.binds
import org.koin.ksp.generated.module
import java.io.File
import kotlin.reflect.full.allSuperclasses

@Single
class Main(
    private val fileSelector: FileSelector,
    private val engine: KotlinJarLoader,
    private val classFileController: ClassFileController,
) {
    // TODO: Command line interface for this
    fun run(args: List<String>): Unit = runBlocking {

//        val testFile = File(File("").absolutePath, "/build/libs/dekotpiler-1.0.0-plain.jar")
        val file = args.getOrNull(0)?.let(::File) ?: fileSelector.selectFile(
            "Select jar",
            description = "Files",
            "*.jar"
        ) ?: return@runBlocking

        val jar = engine.load(file)

        val target = (args.getOrNull(1) ?: run {
            print("Enter fully qualified class name: ")
            readln()
        }).let(::KtReferenceType)
            .takeIf { it in jar } ?: return@runBlocking println("Class file not found in jar.")

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
            val koin = startKoin {
                fixKoinDeepInheritance()
                modules(DekotpilerModule.module)
            }.koin
            val main: Main by koin.inject()
            main.run(args.toList())
        }

        @OptIn(KoinInternalApi::class)
        private fun fixKoinDeepInheritance() {
            // this is slow but koin does not do it automatically
            // with ksp plugin
            DekotpilerModule.module
                .mappings
                .map { (_, inst) -> inst.beanDefinition }
                .forEach { def ->
                    def.binds(def.primaryType.allSuperclasses.toList())
                }
        }


    }
}

@Module
@ComponentScan
object DekotpilerModule